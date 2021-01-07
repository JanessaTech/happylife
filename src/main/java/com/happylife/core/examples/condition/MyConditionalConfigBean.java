package com.happylife.core.examples.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="spring.config.enabled",havingValue = "true")
public class MyConditionalConfigBean {

    @Bean
    public MyConditionalPerson wife(){
        return new MyConditionalPerson("juan", 20);
    }

    @Bean
    public MyConditionalPerson husband(){
        return new MyConditionalPerson("wei", 21);
    }
}
