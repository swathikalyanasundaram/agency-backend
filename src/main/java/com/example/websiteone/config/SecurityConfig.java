package com.example.websiteone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 1. Allow all preflight OPTIONS requests
                        .requestMatchers(HttpMethod.POST, "/api/leads").permitAll() // 2. Explicitly allow public lead creation
                        .requestMatchers(HttpMethod.GET, "/api/leads").hasRole("ADMIN") // 3. Protect GET requests for admins only
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("Admin@123") // Set your chosen password here
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
