package com.example.warehousedeliveryorder.get_set;

import com.google.gson.annotations.SerializedName;

public class ResponseScanBarang {

    @SerializedName("kode_kedatangan")
    private String kodeKedatangan;

    @SerializedName("kode_produk")
    private String kodeBahan;

    @SerializedName("nama_produk")
    private String namaBahan;

    @SerializedName("isi_kedatangan")
    private String isi;

    @SerializedName("bin_location")
    private String binLocation;

    @SerializedName("expired_date")
    private String ed;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public String getEd() {
        return ed;
    }

    public String getIsi() {
        return isi;
    }

    public String getKodeBahan() {
        return kodeBahan;
    }

    public String getKodeKedatangan() {
        return kodeKedatangan;
    }

    public String getNamaBahan() {
        return namaBahan;
    }
}
