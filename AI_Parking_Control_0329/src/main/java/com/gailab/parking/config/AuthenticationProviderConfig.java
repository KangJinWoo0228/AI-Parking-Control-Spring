package com.gailab.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gailab.parking.service.ManagerService;
import com.gailab.parking.service.NormalUserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationProviderConfig {

    private final NormalUserService normalUserService;
    private final ManagerService managerService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider normalUserDaoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(normalUserService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider managerDaoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(managerService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}


