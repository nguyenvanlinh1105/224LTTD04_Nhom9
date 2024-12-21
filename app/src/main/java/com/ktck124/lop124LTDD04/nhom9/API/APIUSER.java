package com.ktck124.lop124LTDD04.nhom9.API;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ktck124.lop124LTDD04.nhom9.Model.Users;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIUSER {

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    // Link API root
    String url = "https://foodtrack-wpjz.onrender.com/";

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:sss").create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    // get list user
    @GET("user/list")
    Call<List<Users>> GetListUser();

    // Add user
    @POST("user/add")
    Call<Users> AddUser(@Body Users model);

    // Update user info
    @POST("user/update")
    Call<Users> UpdateInfo(@Body Users model);
}