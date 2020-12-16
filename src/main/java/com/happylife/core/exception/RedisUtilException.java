package com.happylife.core.exception;

public class RedisUtilException extends Exception{
    public RedisUtilException(String msg){
        super(msg);
    }
    public RedisUtilException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
