package com.happylife.core.common;

public class Constants {
    public final static String AUTHORIZATION = "AUTHORIZATION";
    public final static String CURRENT_USER_NAME = "CURRENT_USER_NAME";
    public final static String BASE_API_PATH = "/tuoke-web/api";
    public final static String BASE_TOKEN_PATH = BASE_API_PATH + "/tokens";
    public enum Test{
        example {@Override public String toString(){ return "example"; }}
    }
}
