package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imius.adapter.TopicAdapter;
import com.example.imius.adapter.TrendingAdapter;
import com.example.imius.databinding.FragmentTopicBinding;
import com.example.imius.databinding.FragmentTrendingBinding;
import com.example.imius.model.TopicModel;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.TopicViewModel;
import com.example.imius.viewmodel.TrendingViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicFragment extends Fragment {
    private FragmentTopicBinding binding;
    private TopicAdapter topicAdapter;
    private TopicViewModel viewModel;

    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTopicBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        binding.fragmentThemeRvTheme.setLayoutManager(linearLayoutManager);

        topicAdapter = new TopicAdapter(this.getContext());
        binding.fragmentThemeRvTheme.setAdapter(topicAdapter);


        viewModel = new ViewModelProvider(getActivity()).get(TopicViewModel.class);
        viewModel.getTopic().observe(getViewLifecycleOwner(), topicModelList -> {
            topicAdapter.setTopicModelList(topicModelList);
            //  Toast.makeText(getContext(), String.valueOf(topicAdapter.getTopicModelList().get(0).getImageTopic()), Toast.LENGTH_LONG).show();
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLiveData();
    }
}