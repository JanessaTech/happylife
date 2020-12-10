package com.happylife.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @RequestMapping(value = "/test")
    public ResponseEntity<Object> test(
            @RequestParam(value = "name", required = false, defaultValue = "") String name
    ){
        return new ResponseEntity<>(String.format("The name is : %s", name), HttpStatus.OK);
    }
}
