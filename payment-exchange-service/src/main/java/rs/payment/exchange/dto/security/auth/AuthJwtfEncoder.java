package rs.payment.exchange.dto.security.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.SerializationException;
import io.jsonwebtoken.io.Serializer;
import rs.payment.exchange.dto.security.JWTF;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class AuthJwtfEncoder implements JWTF.Encoder<AuthInfo> {

    private static class JsonbSerializer implements Serializer<Map<String, ?>> {
        // official json-b documentation claims jsonb instances are thread safe
        private static final Jsonb jsonb = JsonbBuilder.create();

        @Override
        public byte[] serialize(Map<String, ?> stringMap) throws SerializationException {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            jsonb.toJson(stringMap, outputStream);
            return outputStream.toByteArray();
        }
    }

    private static final JsonbSerializer jsonbSerializer = new JsonbSerializer();

    private static final String AUTH_INFO_CLAIM_NAME = "authInfo";

    private final Key signingKey;

    public AuthJwtfEncoder(Key signingKey) {
        this.signingKey = signingKey;
    }

    private Date calculateExpiration() {
        return Date.from(ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS).toInstant().plusSeconds(86400L));
    }

    @Override
    public JWTF.ProcessingState encodeToJwt(JWTF<AuthInfo> jwtf, StringBuilder jwt) {
        Instant currentInstant = Instant.now();
        Date now = Date.from(currentInstant);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString()) //Maybe change to be controlled externally
                .setIssuer(jwtf.getIss())
                .setSubject(jwtf.getSub())
                .setAudience((jwtf.getAud() != null && jwtf.getAud().length > 0) ? jwtf.getAud()[0] : null)
                //.setExpiration(Date.from(currentInstant.with(LocalTime.of(23, 59, 59))))
                .setExpiration(calculateExpiration())
                .setNotBefore(now)
                .setIssuedAt(now)
                .claim(AUTH_INFO_CLAIM_NAME, jwtf.getPayload())
                .serializeToJsonWith(jsonbSerializer)
                .signWith(signingKey, SignatureAlgorithm.forSigningKey(signingKey));

        jwt.append(jwtBuilder.compact());

        return JWTF.ProcessingState.success();
    }
}
