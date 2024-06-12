package com.cau.web.dto;

public class ApiResponseNormal<T> {
    private int code;
    private T data;
    private String msg;
    private String token;

    // Constructor, getters, and setters...


    public ApiResponseNormal(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    // Getters and setters...
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
