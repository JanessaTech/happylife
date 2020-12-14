package com.happylife.core.exception;


public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String msg){
        super(msg);
    }
    public EntityNotFoundException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
