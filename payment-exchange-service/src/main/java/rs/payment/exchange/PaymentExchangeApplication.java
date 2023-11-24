package rs.payment.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableTransactionManagement
//@EnableSwagger2



        @SpringBootApplication()
        @EnableWebMvc
        @ComponentScan({"rs.payment.exchange.*"})
        public class PaymentExchangeApplication {
            public static void main(String[] args) {
                SpringApplication.run(PaymentExchangeApplication.class, args);
            }
        }