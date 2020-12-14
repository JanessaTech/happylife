package com.happylife.core.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int code;
    private String msg;
    private T data;

    private Response(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private Response(int code, String msg, T data){
        this(code, msg);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response success(T data){
        Response response = new Response(0, "success", data);
        return response;
    }

    public static <T> Response success(String msg){
        Response response = new Response(0, msg);
        return response;
    }

    public static <T> Response success(){
        Response response = new Response(0, "success");
        return response;
    }

    public static <T> Response fail(String msg){
        Response response = new Response(-1, msg);
        return response;
    }
}
