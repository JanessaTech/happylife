package com.happylife.core.config;

import com.happylife.core.common.Constants;
import com.happylife.core.component.CurrentUserMethodArgumentResolver;
import com.happylife.core.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public AuthorizationInterceptor loginRequiredInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver(){
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequiredInterceptor())
                .addPathPatterns(Constants.BASE_API_PATH + "/**")
                .excludePathPatterns(Constants.BASE_TOKEN_PATH + "/login");
        super.addInterceptors(registry);
    }
}
