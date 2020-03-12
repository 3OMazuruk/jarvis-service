package ua.com.jarvis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableResourceServer
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);

    }
}
