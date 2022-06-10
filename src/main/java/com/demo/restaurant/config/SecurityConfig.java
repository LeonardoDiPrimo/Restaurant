package com.demo.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //By using spring security, it blocks all routes without authentication
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //This allows enter the Swagger UI interface without the need for a login
        return (web) -> web.ignoring().antMatchers("/**");
    }
}
