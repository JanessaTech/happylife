package com.happylife.core.exception;

public class LoginException extends Exception{
    public LoginException(String msg){
        super(msg);
    }
    public LoginException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
