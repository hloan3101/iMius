package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.imius.adapter.SingerAdapter;
import com.example.imius.adapter.TrendingAdapter;
import com.example.imius.databinding.FragmentSingerBinding;
import com.example.imius.databinding.FragmentTrendingBinding;
import com.example.imius.model.Singer;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.SingerViewModel;
import com.example.imius.viewmodel.TrendingViewModel;

import java.util.ArrayList;


public class SingerFragment extends Fragment {

    private FragmentSingerBinding binding;
    private SingerAdapter singerAdapter;
    private SingerViewModel viewModel;


    public static SingerFragment newInstance() {
        SingerFragment fragment = new SingerFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSingerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        binding.fragmentSingerRvSinger.setLayoutManager(linearLayoutManager);

        singerAdapter = new SingerAdapter(this.getContext());
        binding.fragmentSingerRvSinger.setAdapter(singerAdapter);

        viewModel = new ViewModelProvider(getActivity()).get(SingerViewModel.class);
        viewModel.getSinger().observe(getViewLifecycleOwner(), singerList -> {
            singerAdapter.setSingerList(singerList);
            Toast.makeText(getContext(), String.valueOf(singerAdapter.getSingerList().get(0).getImageSinger()), Toast.LENGTH_LONG).show();
            //      binding.fragmentSingerTvSinger.setText(String.valueOf(singerList.size()));
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLiveData();
    }


}
