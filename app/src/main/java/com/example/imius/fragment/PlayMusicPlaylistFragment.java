package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.adapter.PlaylistPlayMusicAdapter;
import com.example.imius.adapter.PlaylistPlayMusicFavoriteAdapter;
import com.example.imius.adapter.PlaylistPlayMusicLibraryAdapter;
import com.example.imius.databinding.FragmentPlayMusicPlaylistBinding;

import com.example.imius.R;

public class PlayMusicPlaylistFragment extends Fragment {
    private FragmentPlayMusicPlaylistBinding binding;
    private PlaylistPlayMusicAdapter adapter;
    private PlaylistPlayMusicLibraryAdapter adapterLibrary;
    private PlaylistPlayMusicFavoriteAdapter adapterFavorite;

    public static PlayMusicPlaylistFragment newInstance() {
        PlayMusicPlaylistFragment fragment = new PlayMusicPlaylistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayMusicPlaylistBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (PlayMusicActivity.songArrayList.size() > 0 ){
            adapter = new PlaylistPlayMusicAdapter(getActivity(), PlayMusicActivity.songArrayList);

            binding.fragmentPlayMusicPlaylistRvPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.fragmentPlayMusicPlaylistRvPlayMusic.setAdapter(adapter);

        }

        if (PlayMusicActivity.songLibraryPlaylistArrayList.size() > 0){
            adapterLibrary = new PlaylistPlayMusicLibraryAdapter(getActivity(), PlayMusicActivity.songLibraryPlaylistArrayList);

            binding.fragmentPlayMusicPlaylistRvPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.fragmentPlayMusicPlaylistRvPlayMusic.setAdapter(adapterLibrary);
        }

        if (PlayMusicActivity.favoriteSongArrayList.size() > 0){
            adapterFavorite = new PlaylistPlayMusicFavoriteAdapter(getActivity(), PlayMusicActivity.favoriteSongArrayList);

            binding.fragmentPlayMusicPlaylistRvPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.fragmentPlayMusicPlaylistRvPlayMusic.setAdapter(adapterFavorite);
        }

        return view;
    }
}