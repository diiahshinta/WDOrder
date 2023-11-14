package com.example.warehousedeliveryorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.warehousedeliveryorder.adapter.MutasiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class DataMutasiActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    MutasiAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    ProgressDialog loading;
    ArrayList<ResponseGudang> responses;
    APIService apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mutasi);

        recyclerView = findViewById(R.id.list_mutasi);
        refreshLayout = findViewById(R.id.swipe_refresh);

        apiInterface = Api.getData().create(APIService.class);
        loading = ProgressDialog.show(DataMutasiActivity.this, null, "Harap Tunggu...", true, false);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.blue_1)));
        getSupportActionBar().setTitle("Serah Terima Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getlist();
        responses = new ArrayList<>();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                getlist();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    void getlist(){
        apiInterface.getListMutasi().enqueue(new Callback<ArrayList<ResponseGudang>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseGudang>> call, retrofit2.Response<ArrayList<ResponseGudang>> response) {
                if (response.isSuccessful()) {
                    responses = response.body();
                    adapter = new MutasiAdapter(DataMutasiActivity.this, responses);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new MutasiAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            TextView id = v.findViewById(R.id.id_tgl);
                            Intent i = new Intent(DataMutasiActivity.this, ScannerFGActivity.class);
                            i.putExtra("id", id.getText().toString());
                            startActivity(i);
                        }
                    });
                    loading.dismiss();
                }else {
                    loading.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ResponseGudang>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<ResponseGudang> itemFilter = new ArrayList<>();
                for (ResponseGudang model : responses){
                    String no = model.getTgl_masuk_gudang().toLowerCase();
                    if (no.contains(newText)){
                        itemFilter.add(model);
                    }
                }
                adapter.setFilter(itemFilter);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}