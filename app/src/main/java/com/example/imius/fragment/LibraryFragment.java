package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imius.R;
import com.example.imius.databinding.ActivityAddMusicToLibraryBinding;

public class LibraryFragment extends Fragment {

    private ActivityAddMusicToLibraryBinding binding;



    public LibraryFragment() {
        // Required empty public constructor
    }


    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMusicToLibraryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    private void init(){

    }
}