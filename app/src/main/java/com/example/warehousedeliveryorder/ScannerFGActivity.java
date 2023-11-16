package com.example.warehousedeliveryorder;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerFGActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView mScannerView;
    FrameLayout camera;
    Button back;
    String id;
    APIService apiService;
    String token;
    ProgressDialog loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_fgactivity);

        camera = findViewById(R.id.frame_layout_camera);
        id = getIntent().getStringExtra("id");
        apiService = Api.getClient2().create(APIService.class);
        back = findViewById(R.id.btn_back);

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9." +
                "eyJhdWQiOiIxIiwianRpIjoiZGFiODVjMTk3YTJiNWNmMzI3NDUxODc1NmVkZTlhNmRiY2ZlNTJjODYxZjc" +
                "4NjczNjg3ZGQ0NTc2MzkzYmI3ZTMyMDE4ODUyYzI3OTczZDIiLCJpYXQiOjE2OTk4NTk0NzguMzgzNzg4LCJ" +
                "uYmYiOjE2OTk4NTk0NzguMzgzNzkyLCJleHAiOjE3MzE0ODE4NzguMzc2MjA0LCJzdWIiOiI3OCIsInNjb3BlcyI" +
                "6WyJkYXRhcHJpbnQuc2NhbiIsImRhdGFwcmludC5rYXJhbnRpbmEiLCJkYXRhcHJpbnQucmVqZWN0IiwiZGF0YXByaW50Lmx1bHVzIl19." +
                "jqdy7cugofSc3hoD-kG78dz22avfSlUXfTQyliFQtPJGxYiIsilx3hGtiuOd5vrfkM4ngQwtTwS44Q9Ko-5StUMkX45ottLCqrqNphjPAz5wp_" +
                "6kWXzeObZxGOaWpr4rn6TaIfgQfiuBNhbP2kbhXgqKjVqwhe0vy2pERTh6fWYq4v18LzufNsA1JS-R_YkYxcXQAHJp8eQGxJv3yZL6Dy0QaDG-" +
                "AZY2ru3oFtYoWXuckTERpu53_9y8lWLOefsDUghAP6im2eq1FkBdPQ4TCG0CdmWvpDB5IMoJ36hErC80RLvbWGQhW63_bHdenxD3zMLdSDcO9AYqQVaabRW474_" +
                "Gas_F8648ysl5gOCcorWrGe6X6wvuXmLDqOBihTDFuY7_P-BcHFgTBvQjihW2-GxtCOPIY0JmduacEhHvZxhmjcJg3927CQR7EFw8yHmxlJ4SuBiFC-" +
                "kmIpRDoDvvMoogc4hM7E8kFMRYPKj63dUmCj0BSJdLR0yW68EftmgAyMU1cGghg6FqBUqUHIuQW7pYzyHM0Wxv6ZdG6kiIjE5c-opuzGzkPSZK-" +
                "fPWdq6qpoMpcfpbRBzpAbQKj8UDMHTX60dhnYf1hlGM-k6pBv6xfPFNejCLpX0tRHeFCT4grVs9IxSRc625aSAYYZrVQvbu-X5eHBXI-6UQp5WlQw0";

        //loading = ProgressDialog.show(ScannerFGActivity.this, null, "Harap Tunggu...", true, false);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ScannerFGActivity.this, DataMutasiActivity.class);
                startActivity(i);
                finish();
            }
        });

        initScannerView();

    }

    @Override
    public void onResume(){
        super.onResume();
        initView();
    }

    private void initScannerView() {
        mScannerView = new ZXingScannerView(ScannerFGActivity.this);
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(this);
        camera.addView(mScannerView);
        mScannerView.startCamera();
    }

    @Override
    public void onStart() {
        mScannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }

    private void doRequestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initScannerView();
            } else {

            }
        }
    }

    @Override
    public void onPause(){
        mScannerView.stopCamera();
        super.onPause();
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();

        Intent i = new Intent(ScannerFGActivity.this, VerifikasiActivity.class);
        i.putExtra("id", id);
        i.putExtra("barcode", replaceString(result));
        startActivity(i);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void initView(){
        mScannerView.resumeCameraPreview(this::handleResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    String replaceString(String string) {
        return string.replaceAll("[^a-zA-Z0-9-/,.]","");
    }
}
