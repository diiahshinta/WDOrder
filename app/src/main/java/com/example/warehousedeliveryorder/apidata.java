package com.example.warehousedeliveryorder;

import com.example.warehousedeliveryorder.get_set.Item;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface apidata {

//    @GET("sjp/list-sby") list-ckg
//    Call<List<Item>> getsjp();

    @GET("api/sjp/{location}")
    Call<List<Item>> getsjp(@Path("location") String location);

}
