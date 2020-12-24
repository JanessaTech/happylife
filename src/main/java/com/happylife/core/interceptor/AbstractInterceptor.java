package com.happylife.core.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.HandlerInterceptor;

public class AbstractInterceptor implements HandlerInterceptor {
    @Autowired
    protected MessageSource messageSource;
}
