package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.R;

public class MusicDiscFragment extends Fragment {

    public MusicDiscFragment() {
        // Required empty public constructor
    }

    public static MusicDiscFragment newInstance() {
        MusicDiscFragment fragment = new MusicDiscFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_disc, container, false);
    }
}