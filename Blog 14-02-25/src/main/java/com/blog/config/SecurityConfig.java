package com.blog.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.blog.service.UserDetailsServiceImpl;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Inject UserDetailsServiceImpl

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(userDetailsService) // Set user details service
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/backend/**").authenticated() // Require authentication for backend URLs
                .anyRequest().permitAll() // Allow all other requests
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/backend/dashboard", true) // Configure login page and default success URL
                .failureUrl("/login?error=Invalid username or password") // Configure failure URL with error parameter
            )
            .logout(logout -> logout.logoutSuccessUrl("/")); // Configure logout success URL

        return http.build(); // Build the security filter chain
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Return BCryptPasswordEncoder
    }
}