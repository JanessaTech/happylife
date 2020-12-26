package com.happylife.core.exception;

public class MyOwnTransactionException extends Exception{
    public MyOwnTransactionException(String msg){
        super(msg);
    }
    public MyOwnTransactionException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
