package com.viktormmarkov.spacedominos.models;

public class Response {
    private String message;
    public final String type = "response";

    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
