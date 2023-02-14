package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.R;

public class PopularityFragment extends Fragment {

    public PopularityFragment() {
        // Required empty public constructor
    }

    public static PopularityFragment newInstance() {
        PopularityFragment fragment = new PopularityFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popularity, container, false);
    }
}