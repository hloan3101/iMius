package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imius.adapter.TopicAdapter;
import com.example.imius.databinding.FragmentTopicBinding;
import com.example.imius.model.TopicModel;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.TopicViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicFragment extends Fragment {
    private FragmentTopicBinding binding;
    private TopicAdapter topicAdapter;
    private TopicViewModel viewModel;
    private DataService dataService;


    public static TopicFragment newInstance(String param1, String param2) {
        TopicFragment fragment = new TopicFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentTopicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(TopicViewModel.class);

        viewModel.getLiveData().observe(getViewLifecycleOwner(), topic ->{
            topicAdapter.setArrayTopic((ArrayList<TopicModel>) topic);
        });

        //getTopic();

        return view;
    }

//    private void getTopic(){
//        Call<ArrayList<TopicModel>> call = dataService.topic();
//        call.enqueue(new Callback<ArrayList<TopicModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<TopicModel>> call, Response<ArrayList<TopicModel>> response) {
//                ArrayList<TopicModel> topicModelArrayList = (ArrayList<TopicModel>) response.body();
//
//                topicAdapter = new TopicAdapter(getActivity(), topicModelArrayList);
//
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//                binding.fragmentThemeRvTheme.setLayoutManager(linearLayoutManager);
//                binding.fragmentThemeRvTheme.setAdapter(topicAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<TopicModel>> call, Throwable t) {
//
//            }
//        });
//
//    }
}