package com.gailab.parking.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gailab.parking.config.filter.JWTCheckFilter;
import com.gailab.parking.config.handler.APILoginFailHandler;
import com.gailab.parking.config.handler.APILoginSuccessHandler;
import com.gailab.parking.config.handler.APIManagerLoginFailHandler;
import com.gailab.parking.config.handler.APIManagerLoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider normalUserDaoAuthenticationProvider;
    private final DaoAuthenticationProvider managerDaoAuthenticationProvider;

    @Bean
    @Order(1)
    public SecurityFilterChain normalUserFilterChain(HttpSecurity http) throws Exception {
        log.info("----------normaluser config----------");

        http
            .cors().and()
            .requestMatchers(request -> request.antMatchers("/api/normal_user/**"))
            .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(config -> config.disable())
            .formLogin(config -> {
                config.loginPage("/api/normal_user/login");
                config.loginProcessingUrl("/api/normal_user/login");
                config.successHandler(new APILoginSuccessHandler());
                config.failureHandler(new APILoginFailHandler());
            })
            .addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(normalUserDaoAuthenticationProvider)
            .authorizeRequests()
                .antMatchers("/api/normal_user/login").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain managerFilterChain(HttpSecurity http) throws Exception {
        log.info("----------manager config----------");

        http
            .cors().and()
            .authorizeRequests(authorizeRequests -> 
                authorizeRequests
                // 임시로 '/api'로 시작하는 모든 경로 허용함, 보안 상 수정해야 함 (2024.05.22)
                    .antMatchers("/api/manager/login", "/api/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .formLogin(formLogin -> {
                formLogin
                    .loginPage("/manager/login")
                    .loginProcessingUrl("/api/manager/login")
                    .successHandler(new APIManagerLoginSuccessHandler())
                    .failureHandler(new APIManagerLoginFailHandler());
            })
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/manager/logout"))
                .logoutSuccessUrl("/manager/login")
                .invalidateHttpSession(true))
            .addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(managerDaoAuthenticationProvider);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

