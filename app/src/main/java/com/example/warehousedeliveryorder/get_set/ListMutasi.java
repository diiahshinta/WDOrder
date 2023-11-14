package com.example.warehousedeliveryorder.get_set;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ListMutasi implements Serializable {

    @Expose
    String id_barang_masuk, tanggal_barang_masuk, shift_barang_masuk, terima_barang_masuk,
    keterangan_barang_masuk, metode;

    public String getMetode() {
        return metode;
    }

    public String getId_barang_masuk() {
        return id_barang_masuk;
    }

    public String getKeterangan_barang_masuk() {
        return keterangan_barang_masuk;
    }

    public String getShift_barang_masuk() {
        return shift_barang_masuk;
    }

    public String getTanggal_barang_masuk() {
        return tanggal_barang_masuk;
    }

    public String getTerima_barang_masuk() {
        return terima_barang_masuk;
    }
}
