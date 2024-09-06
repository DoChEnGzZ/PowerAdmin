package com.chengnianzhi.poweradmin_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.security.Security;

@Configuration
public class SecurityConfig {


    // 打开cors跨域访问
    @Bean(value = "corsConfigurationSource") // 按名称注入的， 因此名称要固定不变
    public static CorsConfigurationSource corsConfigurationSource() {
        var configSource = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        // 允许跨域访问
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }
}
