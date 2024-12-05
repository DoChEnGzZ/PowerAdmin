package com.chengnianzhi.poweradmin_api.config;

import com.chengnianzhi.poweradmin_api.constant.HttpHeader;
import com.chengnianzhi.poweradmin_api.infra.global.AccessFilter;
import com.chengnianzhi.poweradmin_api.infra.security.SecurityTokenFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Set;

@Configuration
public class GlobalConfig {
    // 这里可以配置一些全局的配置

    // 配置访问过滤器，可以对请求进行的报文和响应的报文进行处理
    @Bean
    public static FilterRegistrationBean<AccessFilter> accessFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        var accessFilter = new AccessFilter(resolver);
        accessFilter.setIncludeHeaders(true);
        accessFilter.setIncludePayload(true);
        accessFilter.setIncludeResponse(true);
        accessFilter.setIncludeQueryString(true);
        accessFilter.setMaxResponseLength(1024 * 1024);
        accessFilter.setMaskHeaders(Set.of(HttpHeader.X_Access_Token));
        var registrationBean = new FilterRegistrationBean<AccessFilter>();
        registrationBean.setFilter(accessFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MIN_VALUE); // 确保它是第一个Filter
        return registrationBean;
    }
}
