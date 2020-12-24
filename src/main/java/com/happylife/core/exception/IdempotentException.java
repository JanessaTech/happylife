package com.happylife.core.exception;

public class IdempotentException extends Exception {
    public IdempotentException(String msg){
        super(msg);
    }
    public IdempotentException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
