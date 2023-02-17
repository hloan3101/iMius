package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.imius.R;

//This is Fragment_insert_nhac_thu_vien class in MUSIC4B

public class LibrarySearchFragment extends Fragment {

    public LibrarySearchFragment() {
        // Required empty public constructor
    }

    public static LibrarySearchFragment newInstance() {
        LibrarySearchFragment fragment = new LibrarySearchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_search, container, false);
    }
}