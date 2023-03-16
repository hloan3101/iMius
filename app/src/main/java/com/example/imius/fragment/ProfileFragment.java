package com.example.imius.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.imius.activity.SignUpActivity;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.FragmentProfileBinding;

import com.example.imius.R;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    private FragmentProfileBinding binding;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        binding.fragmentProfileTvUsername.setText(DataLocalManager.getUsernameData());

        init();

        return view;
    }

    public void init(){
        binding.fragmentProfileTvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callUpdateProfileDialog();
            }
        });

        binding.fragmentProfileCivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callUpdateProfileDialog();
            }
        });

        binding.fragmentProfileIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callUpdateProfileDialog();
            }
        });

        binding.fragmentProfileBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void callUpdateProfileDialog() {
        DialogFragment updateProfileDialog = UpdateProfileDialog.newInstance();
        updateProfileDialog.show(getActivity().getSupportFragmentManager(),"Update Profile Dialog");

    }

    @Override
    public void onClick(View view) {

    }
}