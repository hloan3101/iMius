package com.example.imius.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imius.R;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.ActivityLoginBinding;
import com.example.imius.databinding.ActivityPlaylistBinding;
import com.example.imius.fragment.ProfileFragment;
import com.example.imius.fragment.SearchFragment;
import com.example.imius.viewmodel.LibraryPlaylistViewModel;
import com.example.imius.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

public class PlaylistActivity extends AppCompatActivity {

    private ActivityPlaylistBinding binding;
    private LibraryPlaylistViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            Picasso.get().load(bundle.getString("imgPlaylistLibrary")).into(binding.activityPlaylistIvViewSong);
            binding.activityPlaylistTvSongName.setText(bundle.getString("nameLibraryPlaylist"));

        }else {
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

    private void initView (){
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
    private void getSongLibraryPlayList () {

    }
}