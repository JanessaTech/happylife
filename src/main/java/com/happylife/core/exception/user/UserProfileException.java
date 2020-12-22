package com.happylife.core.exception.user;

public class UserProfileException extends Exception {
    public UserProfileException(String msg){
        super(msg);
    }
    public UserProfileException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
