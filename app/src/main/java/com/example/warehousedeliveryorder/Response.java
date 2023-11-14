package com.example.warehousedeliveryorder;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
