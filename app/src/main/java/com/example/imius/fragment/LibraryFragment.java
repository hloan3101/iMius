package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.imius.R;
import com.example.imius.databinding.FragmentLibraryBinding;

public class LibraryFragment extends Fragment {

    private FragmentLibraryBinding binding;

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
        binding.fragmentLibraryIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialogAddToLibraryPlaylist();
            }
        });
    }

    private void callDialogAddToLibraryPlaylist(){
//        DialogFragment dialogFragment = DialogAddToLibraryPlaylist.newInstance();
//        dialogFragment.show(getActivity().getSupportFragmentManager(), "DialogAddToLibraryPlaylist");
    }
}