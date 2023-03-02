package com.example.imius.repository;

import com.example.imius.api.API;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.service.UserService;

import retrofit2.Call;

public class UserRepository {
    private UserService  userService;

    public UserRepository() {
        this.userService = getUserService();
    }

    public Call<BaseResponse> login(User user){
        return userService.login(user.getUsername(), user.getPassword());
    }

    public UserService getUserService() {
        return API.getAccount().create(UserService.class);
    }
}
