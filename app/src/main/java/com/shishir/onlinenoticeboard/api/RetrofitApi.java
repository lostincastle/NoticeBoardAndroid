package com.shishir.onlinenoticeboard.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    public static String token = "";
    public static Retrofit getInstance(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:8899")
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
