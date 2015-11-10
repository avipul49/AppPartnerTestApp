package com.apppartner.androidprogrammertest.models;

/**
 * Created by vipulmittal on 06/11/15.
 */
public class Response {
    private String code;
    private String message;

    public boolean isSuccess() {
        return code.equals("Success");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
