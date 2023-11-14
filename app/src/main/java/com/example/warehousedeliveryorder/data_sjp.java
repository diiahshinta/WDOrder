package com.example.warehousedeliveryorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.warehousedeliveryorder.adapter.SJPAdapter;
import com.example.warehousedeliveryorder.adapter.adapter_sjp;
import com.example.warehousedeliveryorder.get_set.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class data_sjp extends AppCompatActivity {

    RecyclerView recyclerView;
    SJPAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog loading;
    List<Item> responseLists;
    APIService apiInterface;
    SwipeRefreshLayout refreshLayout;
    String apiLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sjp);
        recyclerView = findViewById(R.id.list_sjp);
        refreshLayout = findViewById(R.id.swipe_refresh);

        Bundle bundle = getIntent().getExtras();
        String idLocation = bundle.getString("location");

        if (idLocation.equals("1")){
            apiLocation = "list-sby";
        } else {
            apiLocation = "list-ckg";
        }

        //Toast.makeText(this, apiLocation, Toast.LENGTH_SHORT).show();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.pink_2)));
        getSupportActionBar().setTitle("Surat Jalan Pengiriman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loading = ProgressDialog.show(data_sjp.this, null, "Loading...", true, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        apiInterface = Api.getClient().create(APIService.class);

        get_data_sjp(apiLocation);

        responseLists = new ArrayList<>();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                get_data_sjp(apiLocation);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void get_data_sjp(String apiLocation) {
        apiInterface.getsjp(apiLocation).enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                Log.d("----tes", new Gson().toJson(response.body()));
                if (response.isSuccessful()){
                    responseLists = response.body();
                    adapter = new SJPAdapter(data_sjp.this, responseLists);
                    recyclerView.setAdapter(adapter);
                    loading.dismiss();
                } else {
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(data_sjp.this, "Server Maintenance : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.lihat_data).setEnabled(false);

        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Item> itemFilter = new ArrayList<>();
                for (Item model : responseLists){
                    String no = model.getNomor_sjp().toLowerCase();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}