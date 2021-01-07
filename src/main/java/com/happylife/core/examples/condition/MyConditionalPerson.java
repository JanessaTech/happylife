package com.happylife.core.examples.condition;

import lombok.Data;

@Data
public class MyConditionalPerson {
    public MyConditionalPerson(String name, int age){
        this.name = name;
        this.age = age;
    }
    private String name;
    private int age;
}
