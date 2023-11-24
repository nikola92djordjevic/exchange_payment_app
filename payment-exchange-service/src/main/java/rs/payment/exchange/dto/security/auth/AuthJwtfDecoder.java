package rs.payment.exchange.dto.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import rs.payment.exchange.dto.security.JWTF;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class AuthJwtfDecoder implements JWTF.Decoder<AuthInfo> {

    private static class JsonbDeserializer implements Deserializer<Map<String, ?>> {
        // official json-b documentation claims jsonb instances are thread safe
        private static final Jsonb jsonb = JsonbBuilder.create();
        private static final Type MAP_RUNTIME_TYPE = new HashMap<String, Object>(){}.getClass().getGenericSuperclass();

        @Override
        public Map<String, ?> deserialize(byte[] bytes) throws DeserializationException {
            return jsonb.fromJson(new ByteArrayInputStream(bytes), MAP_RUNTIME_TYPE);
        }
    }

    private static final String AUTH_INFO_CLAIM_NAME = "authInfo";

    private static final JsonbDeserializer jsonbDeserializer = new JsonbDeserializer();
    private static final Jsonb authInfoJsonb = JsonbBuilder.create();

    private final Key signingKey;

    public AuthJwtfDecoder(Key signingKey) {
        this.signingKey = signingKey;
    }

    private String[] parseAudience(String audinece) {
        return audinece != null ? new String[]{audinece} : null;
    }

    @Override
    public JWTF.ProcessingState decodeFromJwt(String jwt, JWTF<AuthInfo> jwtf) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .deserializeJsonWith(jsonbDeserializer)
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            jwtf.setJti(claims.getId());
            jwtf.setIss(claims.getIssuer());
            jwtf.setSub(claims.getSubject());
            jwtf.setAud(parseAudience(claims.getAudience()));
            jwtf.setExp(claims.getExpiration().getTime());
            jwtf.setNbf(claims.getNotBefore().getTime());
            jwtf.setIat(claims.getIssuedAt().getTime());

            Object au = claims.get(AUTH_INFO_CLAIM_NAME);
            AuthInfo authInfo = authInfoJsonb.fromJson(authInfoJsonb.toJson(au), AuthInfo.class);

            jwtf.setPayload(authInfo);

            return JWTF.ProcessingState.success();
        } catch (JwtException e) {
            return JWTF.ProcessingState.error(e.getMessage());
        }

    }

}
