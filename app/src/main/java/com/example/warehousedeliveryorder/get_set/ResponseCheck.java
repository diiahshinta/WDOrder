package com.example.warehousedeliveryorder.get_set;

import com.google.gson.annotations.SerializedName;

public class ResponseCheck {

    @SerializedName("data")
    private Data2 products;

    public Data2 getProducts() {
        return products;
    }
}
