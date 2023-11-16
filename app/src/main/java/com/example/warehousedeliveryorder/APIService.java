package com.example.warehousedeliveryorder;



import com.example.warehousedeliveryorder.get_set.Item;
import com.example.warehousedeliveryorder.get_set.ResponseCheck;
import com.example.warehousedeliveryorder.get_set.ResponseFG;
import com.example.warehousedeliveryorder.get_set.ResponseScanBarang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("api/sjp/{location}")
    Call<List<Item>> getsjp(@Path("location") String location);


    @GET("api/add_sjp/{id_sjp}/{barcode}")
    Call<Response> savePost(@Path("id_sjp") String id_sjp,
                                     @Path(value = "barcode", encoded = true) String barcode);

    @GET("api/sertem/list-gudang")
    Call<ArrayList<ResponseGudang>> getListMutasi();

    @GET("api/sertem/add/{id}/{barcode}")
    Call<ResponseFG> saveFG(@Path("id") String id,
                            @Path(value = "barcode", encoded = true) String barcode);

    @GET("sjp")
    Call<ArrayList<ResponseKonfirmasi>> getData(@Query("barcode") String barcode);

    @GET("api/sertem/verifikasi/{id_dokumen}/{barcode}")
    Call<ResponseVer> verifikasi(@Path("id_dokumen") String id,
                              @Path(value = "barcode", encoded = true) String barcode);

    @FormUrlEncoded
    @POST("api-bahan/qr")
    Call<ResponseScanBarang> getDataBarang(@Field("id")String qr,
                                           @Field("location") String id_location);

    @GET("dataprint")
    Call<ResponseCheck> getStatus(@Header("Authorization") String token,
                                  @Query("barcode") String barcode,
                                  @Query("level") String level);
}
