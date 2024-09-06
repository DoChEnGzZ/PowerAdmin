package com.chengnianzhi.poweradmin_api.config;

import com.chengnianzhi.poweradmin_api.infra.global.AccessFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class GlobalConfig {
    // 这里可以配置一些全局的配置

    // 配置访问过滤器，可以对请求进行的报文和响应的报文进行处理
    @Bean
    public static FilterRegistrationBean<AccessFilter> accessFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        var accessFilter = new AccessFilter(resolver);
        /** 其它属性配置 **/
        var registrationBean = new FilterRegistrationBean<AccessFilter>();
        registrationBean.setFilter(accessFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MIN_VALUE); // 确保它是第一个Filter
        return registrationBean;
    }
}
