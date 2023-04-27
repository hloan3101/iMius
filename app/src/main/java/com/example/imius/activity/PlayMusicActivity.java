package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.imius.R;
import com.example.imius.model.Song;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlayMusicActivity extends AppCompatActivity {
    private static ArrayList<Song> songList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);


    }

//    private void getDataIntent() {
//        Intent intent = getIntent();
//        songList.clear();
//        if (intent != null){
//            if (intent.hasExtra("song")){
//                Song song = intent.getParcelableExtra("song");
//                songList.add(song);
//            }
//        }
//    }
}