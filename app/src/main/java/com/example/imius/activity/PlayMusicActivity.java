package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.imius.constants.Constants;
import com.example.imius.databinding.ActivityPlayMusicBinding;

import com.example.imius.R;
import com.example.imius.fragment.LibraryPlaylistFragment;
import com.example.imius.fragment.MusicDiscFragment;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.Song;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.model.User;
import com.example.imius.viewmodel.SongViewModel;
import com.example.imius.widget.DiscViewPager;
import com.example.imius.widget.ForegroundServiceControl;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class PlayMusicActivity extends AppCompatActivity implements View.OnClickListener{

//    private ActivityPlayMusicBinding binding;
//    private SongViewModel viewModel;
//    private SQLiteDatabase db;
//
//    private String username;
//
//    private int index = 0, position = 0, duration = 0, timeValue = 0, durationToService = 0;
//
//    private int idLike = 0;
//
//    private boolean repeat = false;
//    private boolean checkRandom = false;
//    private boolean isPlaying;
//
//    private MusicDiscFragment musicDiscFragment;
//    private static DiscViewPager discViewPager;
//
//    private static ArrayList<Song> songArrayList = new ArrayList<>();
//    private static ArrayList<SongLibraryPlaylist> songLibraryPlaylistArrayList = new ArrayList<>();
//    private static ArrayList<FavoriteSong> favoriteSongArrayList = new ArrayList<>();

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (intent != null){
//                isPlaying = intent.getBooleanExtra(getResources().getString(R.string.status_player), false );
//                int action = intent.getIntExtra(getResources().getString(R.string.action_music), 0);
//                duration = intent.getIntExtra(getResources().getString(R.string.duration_music), 0);
//                timeValue = intent.getIntExtra(getResources().getString(R.string.seek_to_music), 0);
//                position = intent.getIntExtra(getResources().getString(R.string.position_music), 0);
//                binding.activityPlayMusicSbSongTime.setProgress(timeValue);
//
//                @SuppressLint("SimpleDateFormat")SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//
//                binding.activityPlayMusicTvRuntime.setText(simpleDateFormat.format(timeValue));
//
//                handleMusic(action);
//                timeSong();
//
//            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        binding = ActivityPlayMusicBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        viewModel = new ViewModelProvider(this).get(SongViewModel.class);
//    //    db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
//        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
//                new IntentFilter(getResources().getString(R.string.send_data_to_activity)));
//
//        getDataFromIntent();
//  //      getDataSQLite();
//        setViewStart();
//        startService();
//        init();
//
//        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
//
//        setContentView(view);
    }

//    private void getDataSQLite() {
//        String sql = "SELECT * FROM user";
//        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToLast();
//        username = cursor.getString(1);
//    }

//    private void getDataFromIntent() {
//        Intent intent = getIntent();
//
//        songArrayList.clear();
//        songLibraryPlaylistArrayList.clear();
//        favoriteSongArrayList.clear();
//
//        if (intent != null){
//            if (intent.hasExtra("song")){
//                Song song = intent.getParcelableExtra("song");
//                songArrayList.add(song);
//
//            } else if (intent.hasExtra("list_song")){
//                songArrayList = intent.getParcelableArrayListExtra("list_song");
//
//            } else if (intent.hasExtra("library_song")){
//                SongLibraryPlaylist songLibraryPlaylist = intent.getParcelableExtra("library_song");
//                songLibraryPlaylistArrayList.add(songLibraryPlaylist);
//
//            } else if (intent.hasExtra("list_song_library")){
//                songLibraryPlaylistArrayList = intent.getParcelableArrayListExtra("list_song_library");
//
//            } else if (intent.hasExtra("favorite_song")){
//                FavoriteSong favoriteSong = intent.getParcelableExtra("favorite_song");
//                favoriteSongArrayList.add(favoriteSong);
//            }
//        }
//    }
//
//    private void sendActionToService(int action){
//        Intent intent = new Intent(this, ForegroundServiceControl.class);
//
//        intent.putExtra(getResources().getString(R.string.action_music_service), action);
//        intent.putExtra(getResources().getString(R.string.duration), durationToService);
//        intent.putExtra(getResources().getString(R.string.repeat_music), repeat);
//        intent.putExtra(getResources().getString(R.string.random_music), checkRandom);
//
//        startService(intent);
//    }
//
//    private void insertLoveSong(int idLike, String username, int idSong, String nameSong,
//                                String nameSinger, String imageSong, String linkSong){
//        Song song = new Song(idSong, nameSong, nameSinger, imageSong, linkSong);
//
//        viewModel.insertLoveSong(idLike, username, song).enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                Toast.makeText(PlayMusicActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void checkLikeSong(String username, int idSong){
//        viewModel.checkLikeSong(username, idSong).enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
//                BaseResponse baseResponse = response.body();
//
//                if (baseResponse != null){
//                    if (baseResponse.getIsSuccess().equals(Constants.successfully)){
//                        index = 1;
//                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_loved);
//                    } else {
//                        index = 0;
//                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_love);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void deleteLikeSong(String username, int idSong){
//        viewModel.deleteLikeSong(username, idSong).enqueue(new Callback<BaseResponse>() {
//            @Override
//            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
////                BaseResponse baseResponse = response.body();
////
////                if (baseResponse != null){
////                    if (baseResponse.getIsSuccess().equals(Constants.successfully)){
////                        index = 0;
////                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_love);
////                    } else {
////                        index = 1;
////                        binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_loved);
////                    }
////                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void startService(){
//        Intent intent = new Intent(this, ForegroundServiceControl.class);
//
//        if (songArrayList.size() > 0 ){
//            intent.putExtra(getResources().getString(R.string.obj_song), songArrayList);
//
//        } else if (songLibraryPlaylistArrayList.size() > 0){
//            intent.putExtra(getResources().getString(R.string.obj_song_library), songLibraryPlaylistArrayList);
//
//        } else if (favoriteSongArrayList.size() > 0){
//            intent.putExtra(getResources().getString(R.string.obj_song_favorite), favoriteSongArrayList);
//
//        }
//
//        startService(intent);
//    }
//
//    private void timeSong() {
//        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//
//        binding.activityPlayMusicTvTotalTime.setText(simpleDateFormat.format(duration));
//        binding.activityPlayMusicSbSongTime.setMax(duration);
//    }
//
//    private void handleMusic(int action) {
//        switch (action){
//            case ForegroundServiceControl.ACTION_PAUSE:
//                binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_pause_button);
//                break;
//            case ForegroundServiceControl.ACTION_RESUME:
//                binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
//                break;
//            case ForegroundServiceControl.ACTION_NEXT:
//                completeNextMusic();
//                break;
//            case ForegroundServiceControl.ACTION_PREVIOUS:
//                completePreviousMusic();
//                break;
//
//        }
//
//    }
//
//    private void nextMusic(){
//        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
//        timeValue = 0;
//    }
//
//    private void previousMusic(){
//        binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
//        timeValue = 0;
//    }
//
//    private void completeNextMusic() {
//        if (songArrayList.size() > 0){
//            nextMusic();
//            setView(username, songArrayList.get(position).getIdSong(),
//                    songArrayList.get(position).getImgSong(),
//                    songArrayList.get(position).getNameSong(),
//                    songArrayList.get(position).getNameSinger());
//
//        } else if (songLibraryPlaylistArrayList.size() > 0){
//            nextMusic();
//            setView(username, songLibraryPlaylistArrayList.get(position).getIdSong(),
//                    songLibraryPlaylistArrayList.get(position).getImgSong(),
//                    songLibraryPlaylistArrayList.get(position).getNameSong(),
//                    songLibraryPlaylistArrayList.get(position).getNameSinger());
//
//        } else if (favoriteSongArrayList.size() > 0){
//            nextMusic();
//            setView(username, favoriteSongArrayList.get(position).getIdSong(),
//                    favoriteSongArrayList.get(position).getImgSong(),
//                    favoriteSongArrayList.get(position).getNameSong(),
//                    favoriteSongArrayList.get(position).getNameSinger());
//        }
//    }
//
//    private void completePreviousMusic() {
//        if (songArrayList.size() > 0){
//            previousMusic();
//            setView(username, songArrayList.get(position).getIdSong(),
//                    songArrayList.get(position).getImgSong(),
//                    songArrayList.get(position).getNameSong(),
//                    songArrayList.get(position).getNameSinger());
//
//        } else if (songLibraryPlaylistArrayList.size() > 0){
//            previousMusic();
//            setView(username, songArrayList.get(position).getIdSong(),
//                    songLibraryPlaylistArrayList.get(position).getImgSong(),
//                    songLibraryPlaylistArrayList.get(position).getNameSong(),
//                    songLibraryPlaylistArrayList.get(position).getNameSinger());
//
//        } else if (favoriteSongArrayList.size() > 0){
//            previousMusic();
//            setView(username, favoriteSongArrayList.get(position).getIdSong(),
//                    favoriteSongArrayList.get(position).getImgSong(),
//                    favoriteSongArrayList.get(position).getNameSong(),
//                    favoriteSongArrayList.get(position).getNameSinger());
//        }
//    }
//
//    private void setView(String username, int idSong, String songImg, String songName, String singerName){
//        setGradient(songImg);
//        musicDiscFragment.playMusicDisc(songImg);
//        Objects.requireNonNull(getSupportActionBar()).setTitle(songName);
//        binding.activityPlayMusicTvSingerName.setText(singerName);
//        binding.activityPlayMusicTvSongName.setText(songName);
//        checkLikeSong(username, idSong);
//
//    }
//
//    @SuppressWarnings("deprecation")
//    private void setViewStart(){
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (songArrayList.size() > 0){
//                    setView(username, songArrayList.get(position).getIdSong(),
//                            songArrayList.get(position).getImgSong(),
//                            songArrayList.get(position).getNameSong(),
//                            songArrayList.get(position).getNameSinger());
//
//                } else if (songLibraryPlaylistArrayList.size() > 0){
//                    setView(username, songArrayList.get(position).getIdSong(),
//                            songLibraryPlaylistArrayList.get(position).getImgSong(),
//                            songLibraryPlaylistArrayList.get(position).getNameSong(),
//                            songLibraryPlaylistArrayList.get(position).getNameSinger());
//
//                } else if (favoriteSongArrayList.size() > 0){
//                    setView(username, favoriteSongArrayList.get(position).getIdSong(),
//                            favoriteSongArrayList.get(position).getImgSong(),
//                            favoriteSongArrayList.get(position).getNameSong(),
//                            favoriteSongArrayList.get(position).getNameSinger());
//                } else {
//                    handler.postDelayed(this,300);
//                }
//            }
//        }, 500);
//    }
//
//    private void setGradient(String songImg){
//        Picasso.get().load(songImg).into(new Target() {
//            @Override
//            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                Palette.from(bitmap).generate(palette -> {
//                    assert  palette != null;
//                    Palette.Swatch swatch = palette.getDominantSwatch();
//                    binding.activityPlayMusicRlContainer.setBackgroundResource(R.drawable.bgr_play_music);
//                    assert  swatch != null;
//                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
//                            new int[]{swatch.getRgb(), swatch.getRgb()});
//
//                    binding.activityPlayMusicRlContainer.setBackground(gradientDrawable);
//                });
//            }
//
//            @Override
//            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//            }
//
//            @Override
//            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//            }
//        });
//    }
//
//    private void init(){
//
//        musicDiscFragment = new MusicDiscFragment();
//        discViewPager = new DiscViewPager(getSupportFragmentManager());
//
//        discViewPager.addFragment(musicDiscFragment);
//        binding.activityPlayMusicVpDiscography.setAdapter(discViewPager);
//
//        setSupportActionBar(binding.activityPlayMusicToolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        binding.activityPlayMusicToolbar.setTitleTextColor(Color.BLACK);
//        musicDiscFragment = (MusicDiscFragment) discViewPager.getItem(position);
//
//
//        binding.activityPlayMusicIbBackSong.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_PREVIOUS));
//
//        binding.activityPlayMusicIbNextSong.setOnClickListener(view -> sendActionToService(ForegroundServiceControl.ACTION_NEXT));
//
//        binding.activityPlayMusicIbPlayAndPauseSong.setOnClickListener(view -> {
//            if (isPlaying){
//                sendActionToService(ForegroundServiceControl.ACTION_PAUSE);
//                binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_pause_button);
//            } else {
//                sendActionToService(ForegroundServiceControl.ACTION_RESUME);
//                binding.activityPlayMusicIbPlayAndPauseSong.setImageResource(R.drawable.ic_play_button);
//            }
//        });
//
//        binding.activityPlayMusicIbRepeatSong.setOnClickListener(this::onClick);
//
//        binding.activityPlayMusicIbRandomSong.setOnClickListener(view -> {
//            if (!checkRandom){
//                if (repeat){
//                    repeat = false;
//                    binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_shuffled);
//                    binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeat_song);
//                } else {
//                    binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_shuffled);
//                }
//                checkRandom = true;
//            } else {
//                binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_random);
//                checkRandom = false;
//            }
//            sendActionToService(ForegroundServiceControl.ACTION_RANDOM);
//        });
//
//        binding.activityPlayMusicIvLoveButton.setOnClickListener(view -> {
//            if (index == 0){
//                Animation animation = AnimationUtils.loadAnimation(PlayMusicActivity.this
//                        , R.anim.anim_love_click);
//                binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_loved);
//                view.startAnimation(animation);
//                if (songArrayList.size() > 0){
//                    insertLoveSong(idLike, username, songArrayList.get(position).getIdSong(),
//                            songArrayList.get(position).getNameSong(),songArrayList.get(position).getNameSinger(),
//                            songArrayList.get(position).getImgSong(), songArrayList.get(position).getLinkSong());
//
//                } else if (songLibraryPlaylistArrayList.size() > 0){
//                    insertLoveSong(idLike, username, songLibraryPlaylistArrayList.get(position).getIdSong(),
//                            songLibraryPlaylistArrayList.get(position).getNameSong(),
//                            songLibraryPlaylistArrayList.get(position).getNameSinger(),
//                            songLibraryPlaylistArrayList.get(position).getImgSong(),
//                            songLibraryPlaylistArrayList.get(position).getLinkSong());
//
//                } else if (favoriteSongArrayList.size() > 0){
//                    insertLoveSong(idLike, username, favoriteSongArrayList.get(position).getIdSong(),
//                            favoriteSongArrayList.get(position).getNameSong(),
//                            favoriteSongArrayList.get(position).getNameSinger(),
//                            favoriteSongArrayList.get(position).getImgSong(),
//                            favoriteSongArrayList.get(position).getLinkSong());
//                }
//
//                index++;
//
//            } else {
//                binding.activityPlayMusicIvLoveButton.setImageResource(R.drawable.ic_love);
//                if (songArrayList.size() > 0){
//                    deleteLikeSong(username, songArrayList.get(position).getIdSong());
//                } else if (songLibraryPlaylistArrayList.size() > 0){
//                    deleteLikeSong(username, songLibraryPlaylistArrayList.get(position).getIdSong());
//                } else if (favoriteSongArrayList.size() > 0){
//                    deleteLikeSong(username, favoriteSongArrayList.get(position).getIdSong());
//                }
//
//                index--;
//            }
//        });
//
//        binding.activityPlayMusicSbSongTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                durationToService = seekBar.getProgress();
//                sendActionToService(ForegroundServiceControl.ACTION_DURATION);
//            }
//        });
//
//        binding.activityPlayMusicToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                songArrayList.clear();
//                songLibraryPlaylistArrayList.clear();
//                favoriteSongArrayList.clear();
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
//    }

    @Override
    public void onClick(View view) {
//        if (!repeat){
//            if (checkRandom){
//                checkRandom = false;
//                binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeated);
//                binding.activityPlayMusicIbRandomSong.setImageResource(R.drawable.ic_random);
//            } else {
//                binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeated);
//            }
//            repeat = true;
//        } else {
//            binding.activityPlayMusicIbRepeatSong.setImageResource(R.drawable.ic_repeat_song);
//            repeat = false;
//        }
//        sendActionToService(ForegroundServiceControl.ACTION_REPEAT);
//    }
    }
}