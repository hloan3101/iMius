package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.imius.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlayMusicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
    }

}