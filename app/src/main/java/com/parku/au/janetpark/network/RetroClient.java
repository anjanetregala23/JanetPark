package com.parku.au.janetpark.network;

import com.parku.au.janetpark.constant.Constant;
import com.parku.au.janetpark.network.service.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Celdane.Lansangan on 2/17/2016.
 */
public class RetroClient {
    private Retrofit retrofit;
    private ApiService apiService;

    // constructor
    public RetroClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HTTP_PROTOCOL + "://" + Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}