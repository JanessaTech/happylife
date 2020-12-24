package com.happylife.core.config;

import com.happylife.core.common.Constants;
import com.happylife.core.interceptor.Auth2Interceptor;
import com.happylife.core.interceptor.IdempotentInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Bean
    public Auth2Interceptor auth2RequiredInterceptor(){
        return new Auth2Interceptor();
    }

    @Bean
    public IdempotentInterceptor idempotentRequiredInterceptor(){
        return new IdempotentInterceptor();
    }

    /*
    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver(){
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }*/

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auth2RequiredInterceptor())
                .addPathPatterns(Constants.BASE_AUTH2_USER_PATH + "/**")
                .excludePathPatterns(Constants.BASE_AUTH2_USER_PATH + "/login");
        registry.addInterceptor(idempotentRequiredInterceptor())
                .addPathPatterns(Constants.BASE_AUTH2_USER_PATH + "/idem");

        super.addInterceptors(registry);
    }

    /**
     * swagger-ui.html cannot be opened if we didn't add the following handlers
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
