package com.happylife.core.examples.configurationproperties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a demo on how to use annotation @ConfigurationProperties and @EnableConfigurationProperties
 * Besides it, we show how to use @Data to make code clean. See details about @Data: https://blog.csdn.net/taiguolaotu/article/details/100893767
 */
@Data
@Component
@ConfigurationProperties(prefix="spring.user.defined")
public class MyComponentProperties {
    private String name;
    private int age;
    private List<Address> addresses = new ArrayList<>();
    private Info info = new Info();

    /**
     * the class must be static, otherwise, spring cannot read properties defined in application.properties
     */
    @Data
    public static class Address{
        private String city;
        private String zipcode;
    }

    @Data
    public static class Info {
        private String lan;
        private String favorite;
    }
}
