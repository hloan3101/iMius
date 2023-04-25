package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.activity.PlayMusicActivity;
import com.example.imius.adapter.PlaylistPlayMusicAdapter;
import com.example.imius.databinding.FragmentPlayMusicPlaylistBinding;

import com.example.imius.R;

public class PlayMusicPlaylistFragment extends Fragment {
    private FragmentPlayMusicPlaylistBinding binding;
    private PlaylistPlayMusicAdapter adapter;

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

        if (PlayMusicActivity.songArrayList.size() > 0){
            adapter = new PlaylistPlayMusicAdapter(getActivity(), PlayMusicActivity.songArrayList);

            binding.fragmentPlayMusicPlaylistRvPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.fragmentPlayMusicPlaylistRvPlayMusic.setAdapter(adapter);

        }

        return view;
    }
}