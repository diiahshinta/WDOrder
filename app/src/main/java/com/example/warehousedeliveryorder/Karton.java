package com.example.warehousedeliveryorder;

import com.google.gson.annotations.SerializedName;

public class Karton {

    @SerializedName("batch")
    private String batch;

    @SerializedName("qty_per_karton")
    private String qtyPerKarton;

    @SerializedName("product")
    private String product;

    @SerializedName("qty_karton")
    private String qtyKarton;

    @SerializedName("qty_subtotal")
    private String qtySubtotal;

    public String getBatch() {
        return batch;
    }

    public String getProduct() {
        return product;
    }

    public String getQtyKarton() {
        return qtyKarton;
    }

    public String getQtyPerKarton() {
        return qtyPerKarton;
    }

    public String getQtySubtotal() {
        return qtySubtotal;
    }
}
