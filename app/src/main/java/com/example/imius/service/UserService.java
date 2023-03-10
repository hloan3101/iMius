package com.example.imius.service;


import android.media.Image;

import com.example.imius.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("login.php")
    Call<BaseResponse> login(@Query("username") String username, @Query("password") String password);

    @POST("sign_up.php")
    Call<BaseResponse> signup(@Query("username") String username, @Query("password") String password,
                              @Query("name") String name, @Query("image") String image);

    @POST("check_email.php")
    Call<BaseResponse> checkEmail(@Query("email") String email);

    @POST("check_username.php")
    Call<BaseResponse> checkUsername(@Query("username") String username);

}
