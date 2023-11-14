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

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiY2M4MDhkZTA2MWM2OTkyMjU2OGUxMGIzNjA2MjEzMTlmMzQwNmFiZGE3ZDYyNTVlZWNlZDZkYjZlMjQwYTJlYWZjYjM5OGFlMmIyNzU5Y2YiLCJpYXQiOjE2OTk1MjY1MTMuNjEyNjU1LCJuYmYiOjE2OTk1MjY1MTMuNjEyNjYxLCJleHAiOjE3MzExNDg5MTMuNTk3NTM0LCJzdWIiOiI3OCIsInNjb3BlcyI6WyJkYXRhcHJpbnQuc2NhbiIsImRhdGFwcmludC5rYXJhbnRpbmEiLCJkYXRhcHJpbnQucmVqZWN0IiwiZGF0YXByaW50Lmx1bHVzIl19.asgLqwFErGFTYSHGbBDIhX4ZcmSrCD4c13vlSHPoBVYb8SB_m8d6FVyQLXBV3PtW0np8473DluHjbsDKPfysbz6d0MhR1UbGbhlhyQKjylNHRRPe2q_k_uPCwa8c1Osf__Gdehj4EzAaCpO1sJp_TIOoxf1D0Ivdpfy0Kd6drO6YHKyG4Px8pi_EX1xK-BgSCP9FzypFI9MevtolGaNTn_44R0_iw-QAwlV3ZU4F7I8iWHMIxuDKcADAhGrhSOpb1j_KUdE_pigu4zubJBXi3tiIkaw-tTlMQrEORIIuW35LOQq68mbT9Oi5a9B0Z6ci33VZFSA1_gt6ZaqV4iCgM-RQjND2LhV-XNh76t_SQsWFBhHuWUzJb7N6kmNGere2WmYKyEpc1PCiElVijyrD4ZefxuO-jrfq1WF9WTpXRbuNlMLyI-xu7haWGF2R6rQvzONn5lC0nC98vrwIHJkQAy6h-fm4BcvtYVSuorTQ03fBXUToafMVu4CgU90ho2kX9hUEzIxrrQHej74LEzVGXmb14Vn9sezuHAW2hs2kl9WMDZ-EYOBCO4GGphAnu-9kOnQvYqWqpl1vIbZKVy6qMpFHfrhvxeH5vqB_euE_AVfxY1JA4QajtmC5Pt5fA-NePyuF0XZRAfxQTSm-DQg5r_oQ7SacKcxCaWdEqZsgI7k";

        loading = ProgressDialog.show(ScannerFGActivity.this, null, "Harap Tunggu...", true, false);

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
