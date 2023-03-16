package com.example.imius.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.imius.R;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.DialogUpdateProfileBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileDialog extends DialogFragment implements View.OnClickListener{

    private DialogUpdateProfileBinding binding;
    private UserViewModel viewModel;

    public static UpdateProfileDialog newInstance(){
        return new UpdateProfileDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogUpdateProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        init();
        setCancelable(false);

        return view;
    }

    public void init(){

        binding.dialogUpdateProfileEtUsername.setText(DataLocalManager.getNameData());

        binding.dialogUpdateProfileIvChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.dialogUpdateProfileBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        binding.dialogUpdateProfileTvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callChangePasswordFragment();
            }
        });

        binding.dialogUpdateProfileTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public boolean checkInput(){
        resetError();

        if (TextUtils.isEmpty(binding.dialogUpdateProfileEtUsername.getText().toString().trim())){
            binding.dialogUpdateProfileEtUsername.setError(getResources().getString(R.string.require));
            return false;
        }

        return true;
    }

    public void resetError(){
        binding.dialogUpdateProfileEtUsername.setError(null);
    }

    public void updateProfile(){
        if (!checkInput())
            return;

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(getResources().getString(R.string.progressbar_tittle));
        progressDialog.setMessage(getResources().getString(R.string.progressbar_login));
        progressDialog.setCancelable(false);
        progressDialog.show();

        viewModel.updateName(binding.dialogUpdateProfileEtUsername.getText().toString()
                , DataLocalManager.getUsernameData()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals("1")){
                        DataLocalManager.setNameData(binding.dialogUpdateProfileEtUsername.getText().toString());

//                        callProfileFragment();
                        Toast.makeText(getContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }

    public void callProfileFragment(){

        Fragment profileFragment = new ProfileFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        binding.dialogUpdateProfileCardview.setVisibility(View.GONE);

        transaction.replace(R.id.dialog_update_profile_frame_content, profileFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    public void callChangePasswordFragment(){

        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        binding.dialogUpdateProfileCardview.setVisibility(View.GONE);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.dialog_update_profile_frame_content, changePasswordFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {

    }
}
