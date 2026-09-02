package com.example.websiteone.config; // Update package if yours differs

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
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow everything without login
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Turn off basic auth login prompt
            .formLogin(formLogin -> formLogin.disable()); // Turn off default form login

        return http.build();
    }
}
