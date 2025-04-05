package com.ums.config;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtResponseFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("api/v1/auth/login", "api/v1/auth/addUser")
                .permitAll()
                .anyRequest().authenticated();
        return http.build();

    }
    @Bean
    public JwtResponseFilter jwtResponseFilter() {
        return new JwtResponseFilter();
    }
}