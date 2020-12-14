package com.happylife.core.exception.user;

public class UserException extends Exception {
    public UserException(String msg){
        super(msg);
    }
    public UserException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
