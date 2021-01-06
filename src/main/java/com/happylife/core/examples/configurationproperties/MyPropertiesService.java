package com.happylife.core.examples.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a demo on how to use annotation @ConfigurationProperties and @EnableConfigurationProperties
 * @EnableConfigurationProperties enables the feature of @ConfigurationProperties first
 * @ConfigurationProperties then tell spring how to reader properties from application.properties
 */
@RestController
@RequestMapping("/tuoke-web/api/examples/property")
public class MyPropertiesService {

    @Autowired
    private MyComponentProperties myComponentProperties;

    @GetMapping
    public ResponseEntity<Object> readProperties(){
        return new ResponseEntity<Object>(myComponentProperties, HttpStatus.OK );
    }

}
