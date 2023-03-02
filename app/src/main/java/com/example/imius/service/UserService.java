package com.example.imius.service;

import com.example.imius.model.BaseResponse;


import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @POST("login.php")
    Call<BaseResponse> login(@Query("username") String username, @Query("password") String password);

}
