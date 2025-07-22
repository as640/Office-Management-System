package com.officemanagement.office.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // ⬅️ Allow signup & login
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // or .httpBasic(Customizer.withDefaults())

        return http.build();
    }
}
