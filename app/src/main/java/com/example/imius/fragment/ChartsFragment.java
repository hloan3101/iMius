package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imius.R;
import com.example.imius.adapter.ChartsAdapter;
import com.example.imius.databinding.FragmentChartsBinding;
import com.example.imius.viewmodel.ChartsViewModel;

public class ChartsFragment extends Fragment {
    private FragmentChartsBinding binding;
    private ChartsAdapter chartsAdapter;
    private ChartsViewModel viewModel;

    // TODO: Rename and change types and number of parameters
    public static ChartsFragment newInstance(String param1, String param2) {
        ChartsFragment fragment = new ChartsFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChartsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        binding.fragmentChartsRvCharts.setLayoutManager(linearLayoutManager);

        chartsAdapter = new ChartsAdapter(this.getContext());
        binding.fragmentChartsRvCharts.setAdapter(chartsAdapter);

        viewModel = new ViewModelProvider(getActivity()).get(ChartsViewModel.class);
        viewModel.getCharts().observe(getViewLifecycleOwner(), chartsModels -> {
            chartsAdapter.setChartsModelList(chartsModels);
            Toast.makeText(getContext(), String.valueOf(chartsAdapter.getChartsModelList().get(0).getImageChart()), Toast.LENGTH_LONG).show();
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLiveData();
    }
}