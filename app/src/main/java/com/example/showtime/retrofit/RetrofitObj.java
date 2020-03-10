package com.example.showtime.retrofit;

import com.example.retrofitproj.Api;
import com.example.showtime.utils.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObj {

    private static Retrofit retrofit = null;

    private static Api api = null;

    public static Api getRetrofitInstance(){
        if(retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(AppConstants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        api =  retrofit.create(Api.class);
        return api;
    }
}
