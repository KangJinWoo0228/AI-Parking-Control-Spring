package com.gailab.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.gailab.parking.config.handler.APIManagerLoginFailHandler;
import com.gailab.parking.config.handler.APIManagerLoginSuccessHandler;


@Configuration
@EnableWebSecurity
public class ManagerSecurityConfig {

    @Bean
    public SecurityFilterChain managerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/manager/login").permitAll()
                .antMatchers("/api/manager/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/api/manager/login")
                .successHandler(new APIManagerLoginSuccessHandler()) // 로그인 성공 핸들러
                .failureHandler(new APIManagerLoginFailHandler()) // 로그인 실패 핸들러
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .csrf().disable();

        return http.build();
    }
}

