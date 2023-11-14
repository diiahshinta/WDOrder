package com.example.warehousedeliveryorder;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dafidzeko on 5/11/2016.
 */
public class RestManager {

    private apidata data ;
    private APIService api_sjp ;

    private OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    public apidata ambil_data(){
        if(data==null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(Constant.URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getRequestHeader())
                    .build();


            data = retrofit.create(apidata.class);

        }
        return data;
    }


    public APIService ambil_data_sjp(){
        if(api_sjp==null){

            Retrofit retrofit =new Retrofit.Builder()
                    .baseUrl(Constant.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRequestHeader())
                    .build();


            api_sjp = retrofit.create(APIService.class);

        }
        return api_sjp;
    }


}
