package com.example.warehousedeliveryorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Button btnsjp, btnfg, btnscan;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle bundle = getIntent().getExtras();
        String idLocation = bundle.getString("id_location");

        btnfg = findViewById(R.id.btn_mutasi);
        btnsjp = findViewById(R.id.btn_sjp);
        btnscan = findViewById(R.id.btn_scan);
        location = findViewById(R.id.title_location);

        if (idLocation.equals("1")){
            location.setText("Kosme\nSIER");
        } else {
            location.setText("Kosme\nCIKARANG");
        }

        btnsjp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, data_sjp.class);
                i.putExtra("location", idLocation);
                startActivity(i);
            }
        });

        btnfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, DataMutasiActivity.class);
                startActivity(i);

            }
        });

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, ScannerBarangActivity.class);
                i.putExtra("id_location", idLocation);
                startActivity(i);

            }
        });
    }
}