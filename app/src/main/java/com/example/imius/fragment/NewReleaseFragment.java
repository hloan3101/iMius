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
import com.example.imius.adapter.NewReleaseAdapter;
import com.example.imius.databinding.FragmentNewReleaseBinding;
import com.example.imius.viewmodel.NewReleaseViewModel;

public class NewReleaseFragment extends Fragment {
    private FragmentNewReleaseBinding binding;
    private NewReleaseAdapter newReleaseAdapter;
    private NewReleaseViewModel viewModel;

    public static NewReleaseFragment newInstance() {
        NewReleaseFragment fragment = new NewReleaseFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewReleaseBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        binding.fragmentPopularityRvPopularity.setLayoutManager(linearLayoutManager);

        newReleaseAdapter = new NewReleaseAdapter(this.getContext());
        binding.fragmentPopularityRvPopularity.setAdapter(newReleaseAdapter);

        viewModel = new ViewModelProvider(getActivity()).get(NewReleaseViewModel.class);
        viewModel.getNewRelease().observe(getViewLifecycleOwner(), newReleaseModels -> {
            newReleaseAdapter.setNewReleaseModelList(newReleaseModels);
       //     Toast.makeText(getContext(), String.valueOf(newReleaseAdapter.getNewReleaseModelList().get(0).getImageNewRelease()), Toast.LENGTH_LONG).show();
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLivaData();
    }
}