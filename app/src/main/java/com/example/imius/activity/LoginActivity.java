package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imius.R;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivityLoginBinding;
import com.example.imius.fragment.ChangePasswordFragment;
import com.example.imius.fragment.ProfileFragment;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        initListener();
    }

    private void initListener (){

        binding.activityLoginEtUsername.setText(DataLocalManager.getUsernameData());

        binding.activityLoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });

        binding.activityLoginTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        binding.activityLoginTvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkInput (){
        resetError();

        boolean check = true;
        if (binding.activityLoginEtUsername.getText().toString().trim().isEmpty()){
            binding.activityLoginTilUsername.setError(getResources().getString(R.string.require));
            check = false;
        }

        if (binding.activityLoginEtPassword.getText().toString().trim().isEmpty()){
            binding.activityLoginTilPassword.setError(getResources().getString(R.string.require));
            check = false;
        }

        return check;
    }

    private void resetError() {
        binding.activityLoginTilUsername.setError(null);
        binding.activityLoginTilPassword.setError(null);
    }

    private void login (){

        if (!checkInput()){
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.progressbar_tittle));
        progressDialog.setMessage(getResources().getString(R.string.progressbar_login));
        progressDialog.setCancelable(false);
        progressDialog.show();


        final User user = new User(binding.activityLoginEtUsername.getText().toString().trim(),
                                    binding.activityLoginEtPassword.getText().toString().trim());

        userViewModel.login(user).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.successfully)){
                        Toast.makeText(LoginActivity.this, getResources().
                                getString(R.string.login_successfully), Toast.LENGTH_LONG).show();

                        DataLocalManager.setUsernameData(user.getUsername());
                        DataLocalManager.setPassword(user.getPassword());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);

                     //   callProfileFragment();
                    //    callChangePasswordFragment();
                        progressDialog.dismiss();
                    }else {
                        Toast.makeText(LoginActivity.this, getResources().getString
                                (R.string.login_failed), Toast.LENGTH_LONG).show();
                        binding.activityLoginTilUsername.setError(" ");
                        binding.activityLoginTilPassword.setError(" ");
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    public void callChangePasswordFragment() {
        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        binding.activityLoginLinearlayout.setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_login_frame_content, changePasswordFragment);
        transaction.commit();

    }

    public void callProfileFragment() {
        ProfileFragment profileFragment = new ProfileFragment();
        binding.activityLoginLinearlayout.setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_login_frame_content, profileFragment);
        transaction.commit();

    }

}