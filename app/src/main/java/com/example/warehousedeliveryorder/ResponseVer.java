package com.example.warehousedeliveryorder;

import com.google.gson.annotations.SerializedName;

public class ResponseVer {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("barcode")
    private String barcode;

    public String getBarcode() {
        return barcode;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
