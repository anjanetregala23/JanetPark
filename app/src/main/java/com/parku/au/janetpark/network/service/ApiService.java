package com.parku.au.janetpark.network.service;


import com.parku.au.janetpark.network.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Celdane.Lansangan on 2/17/2016.
 */
public interface ApiService {
    @POST("user/register")
    Call<User> registerUser(@Body User user);

    @POST("user/login")
    Call<User> loginUser(@Body User user);

}
