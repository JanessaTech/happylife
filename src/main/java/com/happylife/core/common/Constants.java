package com.happylife.core.common;

public class Constants {
    public final static String BASE_API_PATH = "/tuoke-web/api";
    public final static String BASE_AUTH2_PATH = BASE_API_PATH + "/auth2";
    public final static String BASE_AUTH2_USER_PATH  = BASE_AUTH2_PATH + "/users";
    // auth2
    public final static String CLIENT_SECRET = "123456";
    public final static String CLIENT_ID ="client_1";
    public final static String PREFIX_AUTHORIZATION = "Basic ";
    public final static String GRANT_TYPE[] = {"password","refresh_token"};
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String GRANT_TYPE_KEY = "grant_type";
    public final static String REFRESH_TOKEN = "refresh_token";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";
    public static final String AUTH2_TOKEN_URL = "http://127.0.0.1:8080/oauth/token";
    public static final String ACCESS_TOKEN_KEY = "access_token";
    // end of auth2

    //idempotent
    public static final String IDEMPOTENT_REQUEST_HEADER = "idem_token";

    public  enum TOKEN_TYPE{
        UUID {@Override public String toString(){return "uuid";}},
        BEARER {@Override public String toString() {return "bearer";}}
    }

    public final static String HEADER_KEY_AUTHORIZATION = "Authorization";
    public enum Test{
        example {@Override public String toString(){ return "example"; }}
    }
}
