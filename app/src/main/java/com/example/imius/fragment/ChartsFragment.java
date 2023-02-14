package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.R;


public class ChartsFragment extends Fragment {
    public ChartsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChartsFragment newInstance(String param1, String param2) {
        ChartsFragment fragment = new ChartsFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }
}