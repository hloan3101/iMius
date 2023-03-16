package com.example.imius;

import android.app.Application;

import com.example.imius.data.DataLocalManager;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
