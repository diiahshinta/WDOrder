package com.example.warehousedeliveryorder;

import com.google.gson.annotations.SerializedName;

public class ResponseKonfirmasi {

    @SerializedName("batch")
    private String batch;

    @SerializedName("product")
    private int product;

    @SerializedName("expired_date")
    private String ed;

    @SerializedName("qty_karton")
    private String totalKarton;

    @SerializedName("qty_subtotal")
    private String subtotal;

    @SerializedName("barcode")
    private String barcode;

    @SerializedName("total_pieces")
    private String totalPcs;

    @SerializedName("qty_per_karton")
    private String totalperKarton;

    @SerializedName("nama")
    private String nama;

    public String getNama() {
        return nama;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getEd() {
        return ed;
    }

    public String getBatch() {
        return batch;
    }

    public int getProduct() {
        return product;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getTotalKarton() {
        return totalKarton;
    }

    public String getTotalPcs() {
        return totalPcs;
    }

    public String getTotalperKarton() {
        return totalperKarton;
    }
}
