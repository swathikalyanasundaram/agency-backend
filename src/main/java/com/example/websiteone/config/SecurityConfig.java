package com.example.websiteone.config; // (Adjust package to match yours)

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
            .csrf(csrf -> csrf.disable()) // Disable CSRF for external API calls
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permit all incoming requests without authentication
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // Disable the basic auth pop-up/challenge
            .formLogin(formLogin -> formLogin.disable()); // Disable default login form redirects

        return http.build();
    }
}
