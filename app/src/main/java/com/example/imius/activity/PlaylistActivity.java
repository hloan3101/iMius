package com.example.imius.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imius.R;

import com.example.imius.data.DataLocalManager;

import com.example.imius.adapter.LibraryPlaylistAdapter;
import com.example.imius.adapter.SongLibraryPlaylistAdapter;
import com.example.imius.api.API;
import com.example.imius.constants.Constants;

import com.example.imius.databinding.ActivityLoginBinding;
import com.example.imius.databinding.ActivityPlaylistBinding;
import com.example.imius.fragment.ProfileFragment;
import com.example.imius.fragment.SearchFragment;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.LibraryPlaylistViewModel;
import com.example.imius.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {

    private ActivityPlaylistBinding binding;
    private LibraryPlaylistViewModel viewModel;
    private SongLibraryPlaylistAdapter adapter;

    private DataService dataService = API.getAccount().create(DataService.class);
    private int idLibraryPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

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
                        Toast.makeText(PlaylistActivity.this, "null", Toast.LENGTH_LONG).show();
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



        initView();

        setContentView(view);
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
                } else {
                    Toast.makeText(PlaylistActivity.this, "null", Toast.LENGTH_LONG).show();
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
                                                Toast.makeText(PlaylistActivity.this,
                                                        getString(R.string.song_library_playlist_delete_success)
                                                        , Toast.LENGTH_LONG).show();
                                                getAllSongLibraryPlaylist();
                                            } else {
                                                Toast.makeText(PlaylistActivity.this,
                                                        getString(R.string.song_library_playlist_delete_failed),
                                                        Toast.LENGTH_LONG).show();
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

}
