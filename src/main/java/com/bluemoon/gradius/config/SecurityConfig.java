package com.bluemoon.gradius.config;

import com.bluemoon.gradius.security.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/agent/**").permitAll() // 에이전트 통신 인증 무시
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/success", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                )
                .authenticationProvider(authProvider);

        return http.build();
    }
}
