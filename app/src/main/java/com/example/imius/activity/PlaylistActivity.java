package com.example.imius.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.adapter.SongAdapter;
import com.example.imius.adapter.SongLibraryPlaylistAdapter;
import com.example.imius.api.API;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivityPlaylistBinding;
import com.example.imius.fragment.SearchFragment;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.ChartsModel;
import com.example.imius.model.Song;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.LibraryPlaylistViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {

    private ActivityPlaylistBinding binding;
    private LibraryPlaylistViewModel viewModel;
    private SongLibraryPlaylistAdapter adapter;
    private SongAdapter songAdapter;

    private DataService dataService = API.getAccount().create(DataService.class);
    private int idLibraryPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        binding.activityPlaylistRvPlaylist.setLayoutManager(new LinearLayoutManager(PlaylistActivity.this));
        adapter = new SongLibraryPlaylistAdapter(PlaylistActivity.this);
        binding.activityPlaylistRvPlaylist.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(LibraryPlaylistViewModel.class);

        if (bundle != null) {
            Picasso.get().load(bundle.getString("imgPlaylistLibrary")).into(binding.activityPlaylistIvViewSong);
            binding.activityPlaylistTvSongName.setText(bundle.getString("nameLibraryPlaylist"));
            idLibraryPlaylist = bundle.getInt("idLibraryPlaylist");
            DataLocalManager.setIdLibraryPlaylist( bundle.getInt("idLibraryPlaylist"));
         //   getAllSongLibraryPlaylist();

            dataService.getSongLibraryPlaylistList(idLibraryPlaylist).enqueue(new Callback<List<SongLibraryPlaylist>>() {
                @Override
                public void onResponse(Call<List<SongLibraryPlaylist>> call, Response<List<SongLibraryPlaylist>> response) {
                    if (response.body() != null) {
                        adapter.setSongLibraryPlaylistList(response.body());
                    } else {
                        StyleableToast.makeText(PlaylistActivity.this, "null",
                                Toast.LENGTH_LONG, R.style.myToast).show();
                    }
                }

                @Override
                public void onFailure(Call<List<SongLibraryPlaylist>> call, Throwable t) {

                }
            });
            deleteSongLibraryPlaylist();


        } else {
            binding.activityPlaylistImAddSong.setVisibility(View.GONE);
        }

        setAdapter();

        initView();

        eventClickFabBtn();

        setContentView(view);
    }

    public void setAdapter (){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        binding.activityPlaylistRvPlaylist.setLayoutManager(new LinearLayoutManager(PlaylistActivity.this));
        adapter = new SongLibraryPlaylistAdapter(PlaylistActivity.this);
        binding.activityPlaylistRvPlaylist.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(LibraryPlaylistViewModel.class);

        if (bundle.getString("nameLibraryPlaylist") != null) {
            Picasso.get().load(bundle.getString("imgPlaylistLibrary")).into(binding.activityPlaylistIvViewSong);
            binding.activityPlaylistTvSongName.setText(bundle.getString("nameLibraryPlaylist"));
            idLibraryPlaylist = bundle.getInt("idLibraryPlaylist");
            //   getAllSongLibraryPlaylist();

            dataService.getSongLibraryPlaylistList(idLibraryPlaylist).enqueue(new Callback<List<SongLibraryPlaylist>>() {
                @Override
                public void onResponse(Call<List<SongLibraryPlaylist>> call, Response<List<SongLibraryPlaylist>> response) {
                    if (response.body() != null) {
                        adapter.setSongLibraryPlaylistList(response.body());
                    } else {
                        StyleableToast.makeText(PlaylistActivity.this, "null",
                                Toast.LENGTH_LONG, R.style.myToast).show();
                    }
                }

                @Override
                public void onFailure(Call<List<SongLibraryPlaylist>> call, Throwable t) {

                }
            });
            deleteSongLibraryPlaylist();


        } else {
            binding.activityPlaylistImAddSong.setVisibility(View.GONE);
            if (bundle.getString("nameSinger") != null){
                songAdapter = new SongAdapter(PlaylistActivity.this);
                binding.activityPlaylistRvPlaylist.setAdapter(songAdapter);
                // if (bundle.getBoolean("checkTrending", false)) {

                Picasso.get().load(bundle.getString("imageSinger")).into(binding.activityPlaylistIvViewSong);
                binding.activityPlaylistTvSongName.setText(bundle.getString("nameSinger"));
                //   getAllSongLibraryPlaylist();
                dataService.getSongSingerList(DataLocalManager.getIdSinger()).enqueue(new Callback<List<Song>>() {
                    @Override
                    public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                        if (response.body() != null) {
                            songAdapter.setListSongs((ArrayList<Song>) response.body());
                        } else {
                            StyleableToast.makeText(PlaylistActivity.this, "null",
                                    Toast.LENGTH_LONG, R.style.myToast).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Song>> call, Throwable t) {

                    }
                });
            }else if (bundle.getString("nameTrending") != null){
                songAdapter = new SongAdapter(PlaylistActivity.this);
                binding.activityPlaylistRvPlaylist.setAdapter(songAdapter);
                // if (bundle.getBoolean("checkTrending", false)) {

                Picasso.get().load(bundle.getString("imageTrending")).into(binding.activityPlaylistIvViewSong);
                binding.activityPlaylistTvSongName.setText(bundle.getString("nameTrending"));
                //   getAllSongLibraryPlaylist();
                dataService.getSongTrendingList(DataLocalManager.getIdTrending())
                        .enqueue(new Callback<List<Song>>() {
                            @Override
                            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                                if (response.body() != null) {
                                    songAdapter.setListSongs((ArrayList<Song>) response.body());
                                } else {
                                    StyleableToast.makeText(PlaylistActivity.this, "null",
                                            Toast.LENGTH_LONG, R.style.myToast).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Song>> call, Throwable t) {

                            }
                        });
            }else if (bundle.getString("nameTopic") != null){
                songAdapter = new SongAdapter(PlaylistActivity.this);
                binding.activityPlaylistRvPlaylist.setAdapter(songAdapter);
                // if (bundle.getBoolean("checkTrending", false)) {

                Picasso.get().load(bundle.getString("imageTopic")).into(binding.activityPlaylistIvViewSong);
                binding.activityPlaylistTvSongName.setText(bundle.getString("nameTopic"));
                //   getAllSongLibraryPlaylist();
                dataService.getSongTopicList(DataLocalManager.getIdTopic())
                        .enqueue(new Callback<List<Song>>() {
                            @Override
                            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                                if (response.body() != null) {
                                    songAdapter.setListSongs((ArrayList<Song>) response.body());
                                } else {
                                    StyleableToast.makeText(PlaylistActivity.this, "null",
                                            Toast.LENGTH_LONG, R.style.myToast).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Song>> call, Throwable t) {

                            }
                        });
            } else if (bundle.getString("nameChart") != null) {
                    binding.activityPlaylistImAddSong.setVisibility(View.GONE);
                    songAdapter = new SongAdapter(PlaylistActivity.this);
                    binding.activityPlaylistRvPlaylist.setAdapter(songAdapter);

                    //Song Chart
                    Picasso.get().load(bundle.getString("imageSong")).into(binding.activityPlaylistIvViewSong);
                    binding.activityPlaylistTvSongName.setText(bundle.getString("nameSong"));
                    DataLocalManager.setIdSongChart(String.valueOf(bundle.getInt("idSongChart")));
                    Toast.makeText(PlaylistActivity.this, bundle.getString("nameSong"), Toast.LENGTH_SHORT).show();
                    dataService.getSongChartList(DataLocalManager.getIdSongChart()).enqueue(new Callback<List<Song>>() {
                        @Override
                        public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                            if (response.body() != null){
                                songAdapter.setListSongs((ArrayList<Song>) response.body());
                            } else {
                                StyleableToast.makeText(PlaylistActivity.this, "null",
                                        Toast.LENGTH_LONG, R.style.myToast).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Song>> call, Throwable t) {

                        }
                    });
                }
        }
    }



    public void callSearchFragment() {
        Bundle bundle = new Bundle();

        String message = "checkAddLibrary";
        bundle.putBoolean(message, true);
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);

        binding.activityPlaylistAppbarLayout.setVisibility(View.GONE);
        binding.activityPlaylistCollapsingToolbar.setVisibility(View.GONE);
        binding.activityPlaylistLinearlayout.setVisibility(View.GONE);
        binding.activityPlaylistNested.setVisibility(View.GONE);
        binding.activityPlaylistFabAction.setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_playlist_coordinator_layout, searchFragment);
        transaction.commit();
    }

    private void initView() {
        binding.activityPlaylistFabAction.setEnabled(false);

        binding.activityPlaylistImAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PlaylistActivity.this, SearchFragment.class);
//
//                intent.putExtra("checkAddLibrary", true);
//
//                startActivity(intent);

                callSearchFragment();

            }
        });

        binding.activityPlaylistImBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAllSongLibraryPlaylist () {
        dataService.getSongLibraryPlaylistList(idLibraryPlaylist).enqueue(new Callback<List<SongLibraryPlaylist>>() {
            @Override
            public void onResponse(Call<List<SongLibraryPlaylist>> call, Response<List<SongLibraryPlaylist>> response) {
                if (response.body() != null) {
                    adapter.setSongLibraryPlaylistList(response.body());
                    eventClickFabBtn();
                } else {
                    StyleableToast.makeText(PlaylistActivity.this, "null",
                            Toast.LENGTH_LONG, R.style.myToast).show();
                }
            }

            @Override
            public void onFailure(Call<List<SongLibraryPlaylist>> call, Throwable t) {

            }
        });
    }

        private void deleteSongLibraryPlaylist(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<SongLibraryPlaylist> songLibraryPlaylistList = adapter.getSongLibraryPlaylistList();
                SongLibraryPlaylist songLibraryPlaylist = songLibraryPlaylistList.get(position);

                if (direction == ItemTouchHelper.LEFT){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlaylistActivity.this);

                    builder.setTitle(getString(R.string.title_dialog_delete));
                    builder.setMessage(getString(R.string.title_dialog_delete_message));
                    builder.setCancelable(false);

                    builder.setPositiveButton(getString(R.string.yes_dialog),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dataService.deleteSongLibraryPlaylist(songLibraryPlaylist.getIdSongLibraryPlaylist())
                                            .enqueue(new Callback<BaseResponse>() {
                                        @Override
                                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                            if (response.body().getIsSuccess().equals(Constants.successfully)) {
                                                StyleableToast.makeText(PlaylistActivity.this,
                                                        getString(R.string.song_library_playlist_delete_success),
                                                        Toast.LENGTH_LONG, R.style.myToast).show();
                                                getAllSongLibraryPlaylist();
                                            } else {
                                                StyleableToast.makeText(PlaylistActivity.this,
                                                        getString(R.string.song_library_playlist_delete_failed),
                                                        Toast.LENGTH_LONG, R.style.myToast).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BaseResponse> call, Throwable t) {

                                        }
                                    });
                                }
                            });

                    builder.setNegativeButton(getString(R.string.no_dialog),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    getAllSongLibraryPlaylist();
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        }).attachToRecyclerView(binding.activityPlaylistRvPlaylist);
    }

    private void eventClickFabBtn(){
        binding.activityPlaylistFabAction.setEnabled(true);
        binding.activityPlaylistFabAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaylistActivity.this, PlayMusicActivity.class);
                if (adapter.getSongLibraryPlaylistList() != null){
                    if (adapter.getSongLibraryPlaylistList().size() > 0){
                        intent.putExtra("list_song_library",
                                (ArrayList<SongLibraryPlaylist>)adapter.getSongLibraryPlaylistList());
                        startActivity(intent);
                    } else {
                        StyleableToast.makeText(PlaylistActivity.this, "The list has no songs at all.",
                                Toast.LENGTH_LONG, R.style.myToast).show();
                    }
                }

            }
        });
    }

}
