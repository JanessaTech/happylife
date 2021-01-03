package com.happylife.core.examples.beanpostprocessor;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Handler {
    String value() default "";
    PostProcessorConstants.HandlerType handleType();
}
