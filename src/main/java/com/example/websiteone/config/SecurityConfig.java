package com.example.websiteone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Completely disable CSRF for external API calls
            .csrf(csrf -> csrf.disable())
            // Authorize all incoming requests without authentication
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // Disable HTTP Basic authentication prompt
            .httpBasic(basic -> basic.disable())
            // Disable default form login page redirection
            .formLogin(form -> form.disable());

        return http.build();
    }
}
