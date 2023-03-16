package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.imius.R;
import com.example.imius.data.MySharedPreferences;
import com.example.imius.databinding.ActivityWelcomeBinding;

public class SplashActivity extends AppCompatActivity {

    private MySharedPreferences mySharedPreferences;
    private static final String KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mySharedPreferences = new MySharedPreferences(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL)){
                    startActivity(HomeActivity.class);
                } else {
                    startActivity(ActivityWelcomeBinding.class);
                    mySharedPreferences.putBoolean(KEY_FIRST_INSTALL, true);
                }
//                nextActivity();
            }
        }, 2000);

    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}