package com.example.imius.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.imius.databinding.DialogAddToLibraryPlaylistBinding;

public class DialogAddToLibraryPlaylist extends DialogFragment {

    private DialogAddToLibraryPlaylistBinding binding;

    public static DialogAddToLibraryPlaylist newInstance () {
        return new DialogAddToLibraryPlaylist();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogAddToLibraryPlaylistBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setCancelable(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewGroup.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) layoutParams);
    }
}
