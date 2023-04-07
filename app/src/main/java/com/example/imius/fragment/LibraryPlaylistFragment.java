package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.imius.R;
import com.example.imius.adapter.LibraryPlaylistAdapter;
import com.example.imius.databinding.FragmentLibraryPlaylistBinding;
import com.example.imius.viewmodel.LibraryPlaylistViewModel;

public class LibraryPlaylistFragment extends Fragment {

    private FragmentLibraryPlaylistBinding binding;
    private LibraryPlaylistViewModel viewModel;
    private LibraryPlaylistAdapter adapter;
    public static LibraryPlaylistFragment newInstance() {
        LibraryPlaylistFragment fragment = new LibraryPlaylistFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLibraryPlaylistBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentLibraryPlaylistRvPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LibraryPlaylistAdapter(this.getContext());
        binding.fragmentLibraryPlaylistRvPlaylist.setAdapter(adapter);


        viewModel = new ViewModelProvider(getActivity()).get(LibraryPlaylistViewModel.class);
        viewModel.getListLibraryPlaylist().observe(getViewLifecycleOwner(), libraryPlaylists -> {
            adapter.setPlaylistLibraryList(libraryPlaylists);
          //  Toast.makeText(getContext(), String.valueOf(adapter.getPlaylistLibraryList().get(1).getNameLibraryPlaylist()), Toast.LENGTH_LONG).show();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLiveData();
    }
}