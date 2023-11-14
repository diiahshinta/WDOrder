package com.example.warehousedeliveryorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    Button ckg, sier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ckg = findViewById(R.id.btn_cikarang);
        sier = findViewById(R.id.btn_sier);

        ckg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, MenuActivity.class);
                intent.putExtra("id_location", "3");
                startActivity(intent);
            }
        });

        sier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, MenuActivity.class);
                intent.putExtra("id_location", "1");
                startActivity(intent);
            }
        });


    }
}