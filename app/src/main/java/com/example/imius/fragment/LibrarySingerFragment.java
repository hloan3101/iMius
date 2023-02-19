package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.imius.R;

public class LibrarySingerFragment extends Fragment {

    public LibrarySingerFragment() {
        // Required empty public constructor
    }

    public static LibrarySingerFragment newInstance() {
        LibrarySingerFragment fragment = new LibrarySingerFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_singer, container, false);
    }
}