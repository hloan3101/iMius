package com.example.imius.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imius.adapter.SingerAdapter;
import com.example.imius.databinding.FragmentSingerBinding;
import com.example.imius.model.Singer;
import com.example.imius.service.DataService;
import com.example.imius.viewmodel.SingerViewModel;

import java.util.ArrayList;

import retrofit2.Call;


public class SingerFragment extends Fragment {

    private FragmentSingerBinding binding;
    private SingerAdapter singerAdapter;
    private SingerViewModel viewModel;
    private DataService dataService;


    public static SingerFragment newInstance() {
        SingerFragment fragment = new SingerFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        binding = FragmentSingerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        singerAdapter = new SingerAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fragmentSingerRvSinger.setLayoutManager(linearLayoutManager);
        binding.fragmentSingerRvSinger.setHasFixedSize(true);
        binding.fragmentSingerRvSinger.setAdapter(singerAdapter);

        viewModel = new ViewModelProvider(getActivity()).get(SingerViewModel.class);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), singer -> {
            singerAdapter.setSingerArrayList((ArrayList<Singer>) singer);

        });


        init();
        //getSinger();

        return view;

    }

    private void init(){
        ArrayList<Singer> singerArrayList = new ArrayList<>();
        singerAdapter = new SingerAdapter(getActivity(), singerArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.fragmentSingerRvSinger.setLayoutManager(linearLayoutManager);
        binding.fragmentSingerRvSinger.setAdapter(singerAdapter);
    }

//    private void getSinger() {
//            Call<ArrayList<Singer>> call = dataService.singer();
//            call.enqueue(new Callback<ArrayList<Singer>>() {
//                @Override
//                public void onResponse(Call<ArrayList<Singer>> call, Response<ArrayList<Singer>> response) {
//                    ArrayList<Singer> singerArrayList = (ArrayList<Singer>) response.body();
//                    if (singerArrayList != null) {
//                        singerAdapter = new SingerAdapter(getActivity(), singerArrayList);
//
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//                        binding.fragmentSingerRvSinger.setLayoutManager(linearLayoutManager);
//                        binding.fragmentSingerRvSinger.setAdapter(singerAdapter);
//                    } else {
//                        Toast.makeText(getContext(), "Singer null", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<Singer>> call, Throwable t) {
//
//                }
//            });
//
//        }
}
