package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.imius.adapter.TrendingAdapter;
import com.example.imius.databinding.FragmentTrendingBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imius.model.Trending;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.TrendingViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingFragment extends Fragment {
    private FragmentTrendingBinding binding;

    private TrendingAdapter trendingAdapter;
    private TrendingViewModel viewModel;

    private DataService dataService;

    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentTrendingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(TrendingViewModel.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), trending ->{
            trendingAdapter.setArrayTrending((List<Trending>) trending);
        });

       getTrending();

        return view;

    }

    private void getTrending() {
        Call<List<Trending>> call = dataService.trending();
        call.enqueue(new Callback<List<Trending>>() {
            @Override
            public void onResponse(Call<List<Trending>> call, Response<List<Trending>> response) {
                ArrayList<Trending> trendingArrayList = (ArrayList<Trending>) response.body();

                trendingAdapter = new TrendingAdapter(getActivity(), trendingArrayList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                binding.fragmentTrendingRvTrending.setLayoutManager(linearLayoutManager);
                binding.fragmentTrendingRvTrending.setAdapter(trendingAdapter);
            }

            @Override
            public void onFailure(Call<List<Trending>> call, Throwable t) {

            }
        });
    }

}