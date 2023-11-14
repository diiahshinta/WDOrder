package com.example.warehousedeliveryorder.get_set;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_sjp {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("barcode")
    @Expose
    private String barcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Item_sjp{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
