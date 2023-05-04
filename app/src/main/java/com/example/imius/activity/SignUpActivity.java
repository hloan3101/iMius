package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.example.imius.R;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivitySignUpBinding;
import com.example.imius.databinding.ActivityWelcomeBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.viewmodel.UserViewModel;

import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;
    private int code;
    private boolean check = true;
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
            public void onClick(View v) {
                   // checkEmail();

                    if (check){
                        resetError();
                        Random random = new Random();
                        code = random.nextInt(8999)+1000;

                        String stringHost = "smtp.gmail.com";

                        Properties properties = System.getProperties();
                        properties.put("mail.smtp.host", stringHost);
                        properties.put("mail.smtp.port", "465");
                        properties.put("mail.smtp.ssl.enable", "true");
                        properties.put("mail.smtp.auth", "true");

                        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(getResources().getString(R.string.email_server),
                                        getResources().getString(R.string.password_email_server));
                            }
                        });

                        MimeMessage mimeMessage = new MimeMessage(session);

                        try {
                            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
                                    binding.activitySignupEtEmail.getText().toString().trim()));

                            mimeMessage.setSubject(getResources().getString(R.string.subject_email));
                            mimeMessage.setText(getResources().getString(R.string.hello_email)+ "\n\n" +
                                    getResources().getString(R.string.content_email) +  code + "\n\n" +
                                    getResources().getString(R.string.end_email));

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Transport.send(mimeMessage);
                                    } catch (MessagingException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });

                            thread.start();
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
        });

        binding.activitySignupTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.activitySignupTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.activitySignupTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkInput(){
        check = true;
        resetError();

        if (TextUtils.isEmpty(binding.activitySignupEtUsername.getText().toString().trim())){
            binding.activitySignupEtUsername.setError(getResources().getString(R.string.require));
            check = false;;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtEmail.getText().toString())){
            binding.activitySignupEtEmail.setError(getResources().getString(R.string.require));
            check = false;
       } else if (!Pattern.matches(getResources().getString(R.string.email_pattern),
                binding.activitySignupEtEmail.getText().toString().trim())) {
            binding.activitySignupEtEmail.setError(getResources().getString(R.string.email_pattern));
            check = false;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtPassword.getText().toString().trim())){
            binding.activitySignupEtPassword.setError(getResources().getString(R.string.require));
            check = false;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtConfirmPassword.getText().toString().trim())) {
            binding.activitySignupEtConfirmPassword.setError(getResources().getString(R.string.require));
            check = false;
//        }else {
//            if (binding.activitySignupEtPassword.getText().toString().trim().equals(
//                    binding.activitySignupEtConfirmPassword.getText().toString().trim())){
//                binding.activitySignupEtConfirmPassword.setError(getResources().getString(R.string.compare_password_require));
//            }
        }

        if (TextUtils.isEmpty(binding.activitySignupEtConfirmCode.getText().toString().trim())){
            binding.activitySignupEtConfirmCode.setError(getResources().getString(R.string.require));
            check = false;
        }else{
            if(Integer.parseInt(binding.activitySignupEtConfirmCode.getText().toString().trim()) != code) {
                binding.activitySignupEtConfirmCode.setError(getResources().getString(R.string.code_error));
                check = false;
            }
        }

        return check;
    }
    public void checkEmail(){

        check = true;

        if (TextUtils.isEmpty(binding.activitySignupEtEmail.getText().toString())) {
            binding.activitySignupEtEmail.setError(getResources().getString(R.string.require));
            check = false;
            return;
        }

        if (TextUtils.isEmpty(binding.activitySignupEtEmail.getText().toString())){
            binding.activitySignupEtEmail.setError(getResources().getString(R.string.require));
            check = false;
            return;
//        } else if (!Pattern.matches(getResources().getString(R.string.email_pattern),
//                binding.activitySignupEtEmail.getText().toString().trim())) {
//            binding.activitySignupTilEmail.setError(getResources().getString(R.string.email_pattern));
//            check = false;
//            return;
        }

        userViewModel.checkEmail(binding.activitySignupEtEmail.getText().toString().trim()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if(baseResponse.getIsSuccess().equals(Constants.successfully)){
                        binding.activitySignupEtEmail.setError(getResources().getString(R.string.email_exist));
                        check = true;
                    }else {
                        check = false;
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
                        StyleableToast.makeText(SignUpActivity.this,
                                getResources().getString(R.string.signup_successfully),
                                Toast.LENGTH_LONG, R.style.myToast).show();

                        DataLocalManager.setEmail(binding.activitySignupEtEmail.getText().toString());
                        DataLocalManager.setPassword(binding.activitySignupEtPassword.getText().toString());
                        DataLocalManager.setCheckEdit(true);
                        callLoginActivity();
                    }

                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });

    }
    private void callLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }

}