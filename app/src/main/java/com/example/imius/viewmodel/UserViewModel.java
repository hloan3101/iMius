package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.repository.UserRepository;

import retrofit2.Call;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository();
    }

    public Call<BaseResponse> login (User user){
        return userRepository.login(user);
    }
}
