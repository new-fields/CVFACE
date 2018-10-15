package com.cvface.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 应用程序配置类,作用是将拦截器注册到拦截器注册表ProductServiceInterceptorAppConfig.java
 */
@Configuration
public class InterceptorAppConfig implements WebMvcConfigurer{
    @Autowired
    private Interceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor).addPathPatterns("/**/*");
    }
}
