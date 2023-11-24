package rs.payment.exchange.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.JWTFWrap;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.interceptor.HeaderParserInterceptor;

import java.util.List;

@Configuration
public class RequestScopeConfig implements WebMvcConfigurer {

    @Bean
    public HeaderParserInterceptor getHeaderParserInterceptor(){
        return new HeaderParserInterceptor();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHeaderParserInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Bean
    @RequestScope
    public JWTF<AuthInfo> authJwtf() {
        return JWTFWrap.produceWrapped();
    }

}
