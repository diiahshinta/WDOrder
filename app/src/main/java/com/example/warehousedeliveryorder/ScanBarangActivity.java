package com.example.warehousedeliveryorder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.warehousedeliveryorder.get_set.ResponseScanBarang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanBarangActivity extends AppCompatActivity {

    TextView kodeKedatangan, kodeBahan, namaBahan, isi, binLocation, btnEd, ed;
    AppCompatImageView back;
    String idLocation, qr;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barang);

        Bundle bundle = getIntent().getExtras();
        idLocation = bundle.getString("id_location");
        qr = bundle.getString("qr");

        kodeKedatangan = findViewById(R.id.kode_kedatangan);
        kodeBahan = findViewById(R.id.kode_bahan);
        namaBahan = findViewById(R.id.nama_bahan);
        isi = findViewById(R.id.isi);
        binLocation = findViewById(R.id.bin_location);
        btnEd = findViewById(R.id.btn_ed);
        ed = findViewById(R.id.expired_date);
        back = findViewById(R.id.btn_back);

        apiService = Api.getClient().create(APIService.class);

        getData(qr, idLocation);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    void getData(String id, String location){

        Call<ResponseScanBarang> response = apiService.getDataBarang(id, location);
        response.enqueue(new Callback<ResponseScanBarang>() {
            @Override
            public void onResponse(Call<ResponseScanBarang> call, Response<ResponseScanBarang> response) {
                ResponseScanBarang data = response.body();

                Log.d("---tes", data.getKodeKedatangan().toString());
                kodeKedatangan.setText(data.getKodeKedatangan());
                kodeBahan.setText(data.getKodeBahan());
                namaBahan.setText(data.getNamaBahan());
                isi.setText(data.getIsi());
                binLocation.setText(data.getBinLocation());
                btnEd.setText(data.getStatus());
                ed.setText(data.getEd());

                if (data.getStatus().equals("Sudah ED")){
                    btnEd.setBackgroundColor(getResources().getColor(R.color.sudah_ed));
                } else if (data.getStatus().equals("ED < 3 Bulan")){
                    btnEd.setBackgroundColor(getResources().getColor(R.color.tiga_bulan));
                } else if (data.getStatus().equals("ED < 6 Bulan")){
                    btnEd.setBackgroundColor(getResources().getColor(R.color.kurang_enam_bulan));
                } else{
                    btnEd.setBackgroundColor(getResources().getColor(R.color.lebih_enam_bulan));
                }
            }

            @Override
            public void onFailure(Call<ResponseScanBarang> call, Throwable t) {

            }
        });
    }
}