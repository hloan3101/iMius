package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.imius.R;
import com.example.imius.constants.Constants;
import com.example.imius.databinding.ActivitySignUpBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;

    private boolean accept = false, aceptEmail = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        View view  = binding.getRoot();
        setContentView(view);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        init();
    }

    private void init(){
        binding.activitySignupBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.activitySignupBtnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.activitySignupTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.activitySignupTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public boolean checkInput(){
        boolean check = true;
        resetError();

        EmailValidator validator = new EmailValidator();

        if (TextUtils.isEmpty(binding.activitySignupEtUsername.getText().toString().trim())){
            binding.activitySignupTilUsername.setError(getResources().getString(R.string.require));
            check = false;;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtEmail.getText().toString())){
            binding.activitySignupTilEmail.setError(getResources().getString(R.string.require));
            check = false;
        } else {
            if (validator.validate(binding.activitySignupEtEmail.getText().toString())){
               checkEmail(binding.activitySignupEtEmail.getText().toString().trim());
               check = true;

            } else{
                binding.activitySignupTilEmail.setError(getResources().getString(R.string.email));
                check = false;
            }

        }

        if (TextUtils.isEmpty(binding.activitySignupEtPassword.getText().toString().trim())){
            binding.activitySignupTilPassword.setError(getResources().getString(R.string.require));
            check = false;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtConfirmPassword.getText().toString().trim())) {
            if (!binding.activitySignupEtConfirmPassword.getText().toString().trim()
                    .equals(binding.activitySignupEtPassword.getText().toString().trim())) {

                binding.activitySignupTilConfirmPassword.setError(getResources().getString(R.string.compare_password_require));
                check = false;
            }
            binding.activitySignupTilConfirmPassword.setError(getResources().getString(R.string.require));
            check = false;
        }

        return check;
    }

    public void checkUsername(String username){
        final User user = new User(binding.activitySignupEtUsername.getText().toString().trim());
        userViewModel.checkUsername(username).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.failed)){
                        binding.activitySignupTilUsername.setError(getResources().getString(R.string.email_exist));
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    public void checkEmail(String email){
        final User user = new User(binding.activitySignupEtEmail.getText().toString().trim());
        userViewModel.checkEmail(email).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.failed)){
                        binding.activitySignupTilEmail.setError(getResources().getString(R.string.email_exist));
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });

    }

    public void resetError(){
        binding.activitySignupEtUsername.setError(null);
        binding.activitySignupEtEmail.setError(null);
        binding.activitySignupEtPassword.setError(null);
        binding.activitySignupEtConfirmPassword.setError(null);
        binding.activitySignupEtConfirmCode.setError(null);
    }

}