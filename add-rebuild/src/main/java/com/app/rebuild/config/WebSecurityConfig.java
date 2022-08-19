package com.app.rebuild.config;

import com.app.rebuild.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.addrebuild.AppConstants.APP_REST_CONTEXT_PATH;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, EncriptionConfig encriptionConfig, UserService userService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(encriptionConfig.getPasswordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .formLogin(form -> form
                        .loginPage(APP_REST_CONTEXT_PATH+"/users/login")
                        .permitAll()
                )
                .authorizeRequests().anyRequest().permitAll();

        return http.build();
    }
}
