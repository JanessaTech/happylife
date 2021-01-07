package com.happylife.core.examples.condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tuoke-web/api/examples/condition")
public class MyConditionService {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public ResponseEntity<Object> get(){
        Map<String, MyConditionalPerson> beans = applicationContext.getBeansOfType(MyConditionalPerson.class);
        return new ResponseEntity<>(beans, HttpStatus.OK);
    }
}
