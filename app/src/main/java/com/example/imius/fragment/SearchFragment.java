package com.example.imius.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.imius.adapter.SongAdapter;



import com.example.imius.R;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.FragmentSearchBinding;
import com.example.imius.model.Song;
import com.example.imius.viewmodel.SongViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding ;
    private SongViewModel songViewModel;
    private SongAdapter songAdapter;
    private ArrayList<Song> listSong;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        songViewModel = new ViewModelProvider(getActivity()).get(SongViewModel.class);

        songAdapter = new SongAdapter(getActivity());

        Bundle bundle = this.getArguments();

        if (bundle != null){
            if (bundle.getBoolean("checkAddLibrary")){
                DataLocalManager.setCheckSearch(true);
            } else {
                DataLocalManager.setCheckSearch(false);
            }
        } else {
            DataLocalManager.setCheckSearch(false);
        }

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.fragmentSearchToolBar);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                songAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.trim().equals("")){
                    findSong(newText);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean checkSearch(){

        return false;
    }

    private void findSong(String key){
        songViewModel.findSong(key).enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listSong = (ArrayList<Song>) response.body();

                if (listSong.size() > 0){
                    songAdapter = new SongAdapter(getActivity(), listSong);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                    binding.fragmentSearchRvSearch.setLayoutManager(linearLayoutManager);
                    binding.fragmentSearchRvSearch.setAdapter(songAdapter);
                    binding.fragmentSearchCantFind.setVisibility(View.GONE);
                    binding.fragmentSearchRvSearch.setVisibility(View.VISIBLE);

              //      Toast.makeText(getContext(), String.valueOf(songAdapter.getItemCount()), Toast.LENGTH_SHORT).show();

                } else {
                    binding.fragmentSearchRvSearch.setVisibility(View.GONE);
                    binding.fragmentSearchCantFind.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(), "cant find", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}