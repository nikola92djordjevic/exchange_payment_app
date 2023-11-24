package rs.payment.exchange.configuration;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

    private static class JsonbDeserializer implements Deserializer<Map<String, ?>> {
        // official json-b documentation claims jsonb instances are thread safe
        private static final Jsonb jsonb = JsonbBuilder.create();
        private static final Type MAP_RUNTIME_TYPE = new HashMap<String, Object>(){}.getClass().getGenericSuperclass();

        @Override
        public Map<String, ?> deserialize(byte[] bytes) throws DeserializationException {
            return jsonb.fromJson(new ByteArrayInputStream(bytes), MAP_RUNTIME_TYPE);
        }
    }

    private static final JsonbDeserializer jsonbDeserializer = new JsonbDeserializer();

    public final String AUTHORIZATION_TOKEN = "authorization";
    private static final Key hmac256SigningKey = new SecretKeySpec(Base64.getDecoder().decode("5lvxDJzEkR57kKDFMlxwtafT52sOZhO6qIljBqsXf/w="), "HmacSHA256");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURI().contains("/api/login")) {
            filterChain.doFilter(request, response);
        } else {
            String authToken = request.getHeader(AUTHORIZATION_TOKEN);

            if(authToken == null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }

            try {
                Jwts.parserBuilder()
                        .deserializeJsonWith(jsonbDeserializer)
                        .setSigningKey(hmac256SigningKey)
                        .build()
                        .parseClaimsJws(authToken);

                filterChain.doFilter(request, response);
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        }

    }
}
