package com.example.imius.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.imius.R;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivitySignUpBinding;
import com.example.imius.databinding.ActivityWelcomeBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.service.UserService;
import com.example.imius.viewmodel.UserViewModel;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;

    private boolean accept = false, aceptEmail = false;

    private int code;

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
                signup();
            }
        });

        binding.activitySignupBtnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                code = random.nextInt(8999)+1000;
                String url = "";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(SignUpActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", binding.activitySignupEtEmail.getText().toString());
                        params.put("code", String.valueOf(code));

                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        binding.activitySignupTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, ActivityWelcomeBinding.class);
                startActivity(intent);
            }
        });

        binding.activitySignupTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
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
//        } else {
//            if (validator.validate(binding.activitySignupEtEmail.getText().toString())){
//                checkEmail(binding.activitySignupEtEmail.getText().toString().trim());
//                check = true;
//
//            } else{
//                binding.activitySignupTilEmail.setError(getResources().getString(R.string.email));
//                check = false;
//            }

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

        if (!Pattern.matches(Constants.EMAIL_PATTERN, binding.activitySignupEtEmail.getText().toString())){
            binding.activitySignupTilEmail.setError(getResources().getString(R.string.email_invalidate));

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
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

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

    public void signup(){
        if (!checkInput()){
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle(getResources().getString(R.string.progressbar_tittle));
        progressDialog.setMessage(getResources().getString(R.string.progressbar_signup));
        progressDialog.setCancelable(false);
        progressDialog.show();

        final User user = new User(binding.activitySignupEtUsername.getText().toString().trim(),
                binding.activitySignupEtUsername.getText().toString().trim(),
                binding.activitySignupEtPassword.getText().toString().trim(),
                binding.activitySignupEtEmail.getText().toString().trim(),
                binding.activitySignupEtConfirmPassword.getText().toString().trim());

        userViewModel.signup(user).enqueue(new Callback<BaseResponse>() {

            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.failed)){
                        binding.activitySignupTilEmail.setError(getResources().getString(R.string.email_exist));
                    } else {
                        Toast.makeText(SignUpActivity.this,
                                getResources().getString(R.string.signup_successfully),
                                Toast.LENGTH_LONG).show();

                        DataLocalManager.setEmail(binding.activitySignupEtEmail.getText().toString());
                        DataLocalManager.setPassword(binding.activitySignupEtPassword.getText().toString());
                        DataLocalManager.setCheckEdit(true);
                        callLoginctivity();
                    }

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });

    }
    private void callLoginctivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

}