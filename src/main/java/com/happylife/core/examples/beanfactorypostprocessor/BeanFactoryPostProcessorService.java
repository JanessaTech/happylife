package com.happylife.core.examples.beanfactorypostprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a demo about how to register a user-defined bean and modify the property of this bean
 * We implemented this functionality without using any annotations like @component provided by spring programmatically
 * BeanDefinitionRegistryPostProcessor and BeanFactoryPostProcessor are keys to implementing this functionality
 */
@RestController
@RequestMapping("/tuoke-web/api/examples/beanfactorypostProcessor")
public class BeanFactoryPostProcessorService {

    @Autowired
    private MyBeanDefinition myBeanDefinition;

    @GetMapping
    public ResponseEntity<Object> getMyBeanDefinition(){
        return new ResponseEntity<>(myBeanDefinition, HttpStatus.OK);
    }

}
