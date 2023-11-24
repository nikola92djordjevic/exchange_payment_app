package rs.payment.exchange.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import rs.payment.exchange.dto.security.HashingUtil;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.JWTFWrap;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.dto.security.auth.AuthJwtfDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;

public class HeaderParserInterceptor implements HandlerInterceptor {

    public final String AUTHORIZATION_TOKEN = "authorization";

    private static final Key hmac256SigningKey = HashingUtil.hmacSha256FromBase64("5lvxDJzEkR57kKDFMlxwtafT52sOZhO6qIljBqsXf/w=");

    @Resource
    private JWTF<AuthInfo> authJwtf;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader(AUTHORIZATION_TOKEN);

        if(authToken != null) {

            JWTF<AuthInfo> jwtf = JWTFWrap.producePlain();
            JWTF.ProcessingState jwtfProcessingState = JWTF.decodeFromJwt(jwtf, authToken, new AuthJwtfDecoder(hmac256SigningKey));

            if(!jwtfProcessingState.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return false;
            }

            authJwtf.wrap(jwtf);

        }

        return true;
    }
}
