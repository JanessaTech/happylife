package com.happylife.core.exception.login;

public class LoginAuth2Exception extends Exception {
    public LoginAuth2Exception(String msg){
        super(msg);
    }
    public LoginAuth2Exception(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
