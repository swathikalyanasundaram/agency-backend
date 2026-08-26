package com.example.websiteone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS and disable CSRF for REST API calls
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("https://yezhuththu.site",
                            "https://agency-frontend-navy.vercel.app", "http://localhost:5500"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to form submissions
                        .requestMatchers(HttpMethod.POST, "/api/leads").permitAll()
                        // Protect GET /api/leads so only logged-in ADMINs can access leads
                        .requestMatchers(HttpMethod.GET, "/api/leads").hasRole("ADMIN")
                        .anyRequest().authenticated())
                // Enable Spring Security default login form
                .formLogin(form -> form
                        .defaultSuccessUrl("https://yezhuththu.site/admin.html", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    // Define Admin Credentials
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("Admin@123") // Replace with your secure password
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}