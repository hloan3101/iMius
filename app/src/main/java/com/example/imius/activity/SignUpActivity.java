package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.imius.R;
import com.example.imius.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}