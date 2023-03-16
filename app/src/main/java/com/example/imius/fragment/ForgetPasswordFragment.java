package com.example.imius.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.imius.activity.LoginActivity;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.FragmentForgetPasswordBinding;

import com.example.imius.R;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.User;
import com.example.imius.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordFragment extends Fragment implements View.OnClickListener{

    private FragmentForgetPasswordBinding binding;
    private UserViewModel viewModel;

    public static ForgetPasswordFragment newInstance() {
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);

        binding.fragmentForgetPasswordBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.fragmentForgetPasswordEtConfirmNewPassword.getText().toString()
                        .equals(binding.fragmentForgetPasswordEtNewPassword.getText().toString())){

                    forgetPassword();
                } else {

                    binding.fragmentForgetPasswordTilConfirmNewPassword.setError("Password is not match !!");
                }
            }
        });

        return view;
    }

    private void forgetPassword(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(getResources().getString(R.string.progressbar_tittle));
        progressDialog.setMessage(getResources().getString(R.string.progressbar_login));
        progressDialog.setCancelable(false);
        progressDialog.show();

        final User user = new User(DataLocalManager.getUsernameData(),
                binding.fragmentForgetPasswordEtConfirmNewPassword.getText().toString());

        viewModel.updatePassword(user).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null){
                    if (baseResponse.getIsSuccess().equals(Constants.successfully)){

                        DataLocalManager.setPassword(user.getPassword());

                            callProfileFragment();

//                        Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        startActivity(intent);

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

            }
        });
    }public void callProfileFragment() {
        ProfileFragment profileFragment = new ProfileFragment();
        binding.fragmentForgetPasswordLinearLayout.setVisibility(View.GONE);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_forget_password_frame_content, profileFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View view) {

    }
}