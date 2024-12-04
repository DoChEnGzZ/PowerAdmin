package com.chengnianzhi.poweradmin_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Security;

@Configuration
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 匹配所有路径
                        .allowedOrigins("http://localhost:3000", "https://example.com") // 允许的跨域源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                        .allowedHeaders("*") // 允许的请求头
                        .allowCredentials(true); // 允许发送 Cookie
            }
        };
    }

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
