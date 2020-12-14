package com.happylife.core.exception.uuid;

public class UUIDException extends Exception{
    public UUIDException(String msg){
        super(msg);
    }
    public UUIDException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
