package tr.com.obss.jss.week3spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tr.com.obss.jss.week3spring.interceptor.RequestInInterceptor;

@SpringBootApplication
public class Week3SpringApplication implements WebMvcConfigurer {

    @Autowired
    private RequestInInterceptor requestInInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(Week3SpringApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInInterceptor);
    }
}
