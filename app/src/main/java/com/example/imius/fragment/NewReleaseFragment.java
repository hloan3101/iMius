package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.R;

public class NewReleaseFragment extends Fragment {

    public NewReleaseFragment() {
        // Required empty public constructor
    }

    public static NewReleaseFragment newInstance() {
        NewReleaseFragment fragment = new NewReleaseFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_release, container, false);
    }
}