package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.imius.R;
import com.example.imius.adapter.ViewPagerLibrary;
import com.example.imius.databinding.FragmentLibraryBinding;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class LibraryFragment extends Fragment {

    private FragmentLibraryBinding binding;
    ViewPager viewPager;
    TabLayout tabLayout;

    public static LibraryFragment newInstance() {
        LibraryFragment fragment = new LibraryFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentLibraryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();
        return view;
    }

    private void init(){

        viewPager = binding.fragmentLibraryViewPager;
        tabLayout = binding.fragmentLibraryTabLayout;

        ViewPagerLibrary viewPagerLibrary = new ViewPagerLibrary(getChildFragmentManager());
        viewPagerLibrary.addFragment(new LibraryPlaylistFragment(), "Playlist");
        viewPagerLibrary.addFragment(new FavoriteFragment(), "Favorite");
        viewPagerLibrary.addFragment(new HistoryFragment(), "History");

        viewPager.setAdapter(viewPagerLibrary);
        tabLayout.setupWithViewPager(viewPager);

        binding.fragmentLibraryIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialogAddToLibraryPlaylist();
            }
        });
    }

    private void callDialogAddToLibraryPlaylist(){
        DialogFragment dialogFragment =DialogAddToLibraryPlaylist.newInstance();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "DialogAddToLibraryPlaylist");
    }
}