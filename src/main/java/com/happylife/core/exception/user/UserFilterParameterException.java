package com.happylife.core.exception.user;

import java.util.ArrayList;
import java.util.List;

public class UserFilterParameterException extends Exception{
    private List<String> invalidFields = new ArrayList<String>();

    public UserFilterParameterException(String msg){
        super(msg);
    }
}
