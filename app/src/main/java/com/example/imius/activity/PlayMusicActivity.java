package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.imius.constants.Constants;
import com.example.imius.databinding.ActivityPlayMusicBinding;

import com.example.imius.R;

import com.example.imius.fragment.MusicDiscFragment;
import com.example.imius.fragment.PlayMusicPlaylistFragment;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.Song;
import com.example.imius.model.SongLibraryPlaylist;

import com.example.imius.viewmodel.SongViewModel;
import com.example.imius.widget.DiscViewPager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusicActivity extends AppCompatActivity {

    private ActivityPlayMusicBinding binding;
    private SongViewModel viewModel;

    private int index = 0, position = 0;

    private String username;
    private boolean repeat = false;
    private boolean checkRandom = false;
    private boolean nextSong = false;

    private MusicDiscFragment musicDiscFragment;
    public static DiscViewPager discViewPager;
    private PlayMusicPlaylistFragment playMusicPlaylistFragment;

    MediaPlayer mediaPlayer;

    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static ArrayList<SongLibraryPlaylist> songLibraryPlaylistArrayList = new ArrayList<>();
    public static ArrayList<FavoriteSong> favoriteSongArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayMusicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
        setContentView(view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getDataFromIntent();
        init();
        eventClick();

        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);

    }

    private void init(){

        musicDiscFragment = new MusicDiscFragment();
        playMusicPlaylistFragment = new PlayMusicPlaylistFragment();
        discViewPager = new DiscViewPager(getSupportFragmentManager());

        discViewPager.addFragment(musicDiscFragment);
        discViewPager.addFragment(playMusicPlaylistFragment);

        binding.activityPlayMusicVpDiscography.setAdapter(discViewPager);

        setSupportActionBar(binding.activityPlayMusicToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        musicDiscFragment = (MusicDiscFragment) discViewPager.getItem(0);

        if (songArrayList.size() > 0){
            getSupportActionBar().setTitle(songArrayList.get(0).getNameSong());
            new PlayMP3().execute(songArrayList.get(0).getLinkSong());
            binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
        }

        if (songLibraryPlaylistArrayList.size() > 0){
            getSupportActionBar().setTitle(songLibraryPlaylistArrayList.get(0).getNameSong());
            new PlayMP3().execute(songLibraryPlaylistArrayList.get(0).getLinkSong());
            binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
        }

        if (favoriteSongArrayList.size() > 0){
            getSupportActionBar().setTitle(favoriteSongArrayList.get(0).getNameSong());
            new PlayMP3().execute(favoriteSongArrayList.get(0).getLinkSong());
            binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
        }

        binding.activityPlayMusicToolbar.setNavigationOnClickListener( view -> {
            finish();

            mediaPlayer.stop();
            songArrayList.clear();
            songLibraryPlaylistArrayList.clear();
            favoriteSongArrayList.clear();
        });

          binding.activityPlayMusicToolbar.setTitleTextColor(Color.BLACK);
    }

    private void eventClick() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (discViewPager.getItem(1) != null){
                    if (songArrayList.size() > 0){
                        musicDiscFragment.playMusicDisc(songArrayList.get(0).getImgSong());
                        handler.removeCallbacks(this);
                    } else if (songLibraryPlaylistArrayList.size() > 0){
                        musicDiscFragment.playMusicDisc(songLibraryPlaylistArrayList.get(0).getImageSong());
                        handler.removeCallbacks(this);
                    } else if (favoriteSongArrayList.size() > 0){
                        musicDiscFragment.playMusicDisc(favoriteSongArrayList.get(0).getImageSong());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }

                }
            }
        },500);


        binding.activityPlayMusicIbBackSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position--;

                        if (position < 0){
                            position = songArrayList.size() - 1;
                        }

                        if (repeat == true){
                            position += 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        new PlayMP3().execute(songArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songArrayList.get(position).getImgSong());
                        getSupportActionBar().setTitle(songArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }
                }

                if (songLibraryPlaylistArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songLibraryPlaylistArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position--;

                        if (position < 0){
                            position = songLibraryPlaylistArrayList.size() - 1;
                        }

                        if (repeat == true){
                            position += 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songLibraryPlaylistArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        new PlayMP3().execute(songLibraryPlaylistArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songLibraryPlaylistArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(songLibraryPlaylistArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }
                }

                if (favoriteSongArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (favoriteSongArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position--;

                        if (position < 0){
                            position = favoriteSongArrayList.size() - 1;
                        }

                        if (repeat == true){
                            position += 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(favoriteSongArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        new PlayMP3().execute(favoriteSongArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(favoriteSongArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(favoriteSongArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }
                }
                binding.activityPlayMusicIbBackSong.setClickable(false);
                binding.activityPlayMusicIbNextSong.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.activityPlayMusicIbBackSong.setClickable(true);
                        binding.activityPlayMusicIbNextSong.setClickable(true);
                    }
                }, 5000);
            }
        });

        binding.activityPlayMusicIbNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (songArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(songArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songArrayList.get(position).getImgSong());
                        getSupportActionBar().setTitle(songArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }

                }

                if (songLibraryPlaylistArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songLibraryPlaylistArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = songLibraryPlaylistArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songLibraryPlaylistArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (songLibraryPlaylistArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(songLibraryPlaylistArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songLibraryPlaylistArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(songLibraryPlaylistArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }

                }

                if (favoriteSongArrayList.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (favoriteSongArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = favoriteSongArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(favoriteSongArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (favoriteSongArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(favoriteSongArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(favoriteSongArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(favoriteSongArrayList.get(position).getNameSong());
                        updateTimeSong();
                    }
                }
                binding.activityPlayMusicIbBackSong.setClickable(false);
                binding.activityPlayMusicIbNextSong.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.activityPlayMusicIbBackSong.setClickable(true);
                        binding.activityPlayMusicIbNextSong.setClickable(true);
                    }
                }, 5000);
            }
        });

        binding.activityPlayMusicIbPlayAndPauseSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_pause_button);
                } else {
                    mediaPlayer.start();
                    binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                }
            }
        });

        binding.activityPlayMusicIbRepeatSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkRandom == true){
                        checkRandom = false;
                        binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeated);
                        binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_random);
                    }
                    binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeated);
                    repeat = true;
                } else {
                    binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeat_song);
                    repeat = false;
                }
            }
        });

        binding.activityPlayMusicIbRandomSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRandom == false){
                    if (repeat == true){
                        repeat = false;
                        binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_shuffled);
                        binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeat_song);
                    }
                    binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_shuffled);
                    checkRandom = true;
                } else {
                    binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_random);
                    checkRandom = false;
                }
            }
        });

        binding.activityPlayMusicIvLoveButton.setOnClickListener(view -> {
            if (index == 0){
                Animation animation = AnimationUtils.loadAnimation(PlayMusicActivity.this
                        , R.anim.anim_love_click);
                binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_loved);
                view.startAnimation(animation);
                if (songArrayList.size() > 0){
                    insertLoveSong(username, songArrayList.get(position).getIdSong(),
                            songArrayList.get(position).getNameSong(),songArrayList.get(position).getNameSinger(),
                            songArrayList.get(position).getImgSong(), songArrayList.get(position).getLinkSong());

                } else if (songLibraryPlaylistArrayList.size() > 0){
                    insertLoveSong(username, songLibraryPlaylistArrayList.get(position).getIdSong(),
                            songLibraryPlaylistArrayList.get(position).getNameSong(),
                            songLibraryPlaylistArrayList.get(position).getNameSinger(),
                            songLibraryPlaylistArrayList.get(position).getImageSong(),
                            songLibraryPlaylistArrayList.get(position).getLinkSong());

                } else if (favoriteSongArrayList.size() > 0){
                    insertLoveSong(username, favoriteSongArrayList.get(position).getIdSong(),
                            favoriteSongArrayList.get(position).getNameSong(),
                            favoriteSongArrayList.get(position).getNameSinger(),
                            favoriteSongArrayList.get(position).getImageSong(),
                            favoriteSongArrayList.get(position).getLinkSong());
                }

                index++;

            } else {
                binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_love);
                if (songArrayList.size() > 0){
                    deleteLikeSong(username, songArrayList.get(position).getIdSong());
                } else if (songLibraryPlaylistArrayList.size() > 0){
                    deleteLikeSong(username, songLibraryPlaylistArrayList.get(position).getIdSong());
                } else if (favoriteSongArrayList.size() > 0){
                    deleteLikeSong(username, favoriteSongArrayList.get(position).getIdSong());
                }

                index--;
            }
        });

        binding.activityPlayMusicSbSongTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());

            }
        });

    }

    private void getDataFromIntent(){
        Intent intent = getIntent();

        songArrayList.clear();
        songLibraryPlaylistArrayList.clear();
        favoriteSongArrayList.clear();

        if (intent != null){
            if (intent.hasExtra("song")){
                Song song = intent.getParcelableExtra("song");
                songArrayList.add(song);
            }

            if (intent.hasExtra("list_song")){
                ArrayList<Song> listSong = intent.getParcelableArrayListExtra("list_song");
                songArrayList = listSong;
            }

            if (intent.hasExtra("library_song")){
                SongLibraryPlaylist songLibrary = intent.getParcelableExtra("library_song");
                songLibraryPlaylistArrayList.add(songLibrary);
            }

            if (intent.hasExtra("list_song_library")){
                ArrayList<SongLibraryPlaylist> listSongLibrary = intent.getParcelableArrayListExtra("list_song_library");
                songLibraryPlaylistArrayList = listSongLibrary;
            }

            if (intent.hasExtra("favorite_song")){
                FavoriteSong favoriteSong = intent.getParcelableExtra("favorite_song");
                favoriteSongArrayList.add(favoriteSong);
            }
        }
    }

    private void insertLoveSong(String username, int idSong, String nameSong,
                                String nameSinger, String imageSong, String linkSong){
        Song song = new Song(idSong, nameSong, nameSinger, imageSong, linkSong);

        viewModel.insertLoveSong(username, song).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Toast.makeText(PlayMusicActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }


    private void checkLikeSong(String username, int idSong){
        viewModel.checkLikeSong(username, idSong).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.successfully)){
                        index = 1;
                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_loved);
                    } else {
                        index = 0;
                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_love);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }



    private void deleteLikeSong(String username, int idSong){
        viewModel.deleteLikeSong(username, idSong).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        binding.activityPlayMusicTvTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        binding.activityPlayMusicSbSongTime.setMax(mediaPlayer.getDuration());
    }

    private void updateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    binding.activityPlayMusicSbSongTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    binding.activityPlayMusicTvRuntime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            nextSong = true;
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (nextSong == true){
                    if (position < (songArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (songArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(songArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songArrayList.get(position).getImgSong());
                        getSupportActionBar().setTitle(songArrayList.get(position).getNameSong());
                    }

                    if (position < (songLibraryPlaylistArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = songLibraryPlaylistArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(songLibraryPlaylistArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (songLibraryPlaylistArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(songLibraryPlaylistArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(songLibraryPlaylistArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(songLibraryPlaylistArrayList.get(position).getNameSong());
                    }

                    if (position < (favoriteSongArrayList.size())){
                        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
                        position++;

                        if (repeat == true){
                            if (position == 0){
                                position = favoriteSongArrayList.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true ){
                            Random random = new Random();
                            index = random.nextInt(favoriteSongArrayList.size());
                            if (index == position){
                                position = index - 1;
                            }

                            position = index;
                        }
                        if (position > (favoriteSongArrayList.size() - 1)){
                            position = 0;
                        }
                        new PlayMP3().execute(favoriteSongArrayList.get(position).getLinkSong());
                        musicDiscFragment.playMusicDisc(favoriteSongArrayList.get(position).getImageSong());
                        getSupportActionBar().setTitle(favoriteSongArrayList.get(position).getNameSong());
                    }

                    binding.activityPlayMusicIbBackSong.setClickable(false);
                    binding.activityPlayMusicIbNextSong.setClickable(false);

                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.activityPlayMusicIbBackSong.setClickable(true);
                            binding.activityPlayMusicIbNextSong.setClickable(true);
                        }
                    }, 5000);
                    nextSong = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        },1000);
    }

    class PlayMP3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();

            } catch (IOException e){
                e.printStackTrace();
            }

            mediaPlayer.start();
            timeSong();
            updateTimeSong();
        }
    }
}