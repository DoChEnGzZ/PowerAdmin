package com.chengnianzhi.poweradmin_api.config;

import com.chengnianzhi.poweradmin_api.infra.security.SecurityAuthenticationEntryPoint;
import com.chengnianzhi.poweradmin_api.infra.security.SecurityTokenFilter;
import com.chengnianzhi.poweradmin_api.service.token.TokenService;
import com.chengnianzhi.poweradmin_api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Security;

@Configuration
public class SecurityConfig {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerExceptionResolver resolver) throws Exception {
        http.cors(Customizer.withDefaults());
        // 基于token的内部rest api服务不需要csrf和session
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        // 位置保持跟UsernamePasswordAuthenticationFilter一致
        // Authentication
        http.addFilterBefore(new SecurityTokenFilter(tokenService, userService), UsernamePasswordAuthenticationFilter.class);
        // Authorization
        http.authorizeHttpRequests((requests) -> requests
                .antMatchers("/login").permitAll() // 要赋予匿名访问权限，否则无法获取token
                .anyRequest().authenticated());
        // 默认是内部Forward到/error，对于API不是好的方式，这里自定义一个错误处理机制。
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new SecurityAuthenticationEntryPoint(resolver)));
        return http.build();
    }

    @Bean
    public static WebSecurityCustomizer webSecurityCustomizer() {
        // 这里ignore的就完全不会进入Security栈了，比如cors
        return (web -> {
        });
    }

    @Bean(value = "corsConfigurationSource") // 按名称注入的， 因此名称要固定不变
    public static CorsConfigurationSource corsConfigurationSource() {
        var configSource = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }
}
