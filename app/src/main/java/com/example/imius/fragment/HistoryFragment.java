package com.example.imius.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.imius.R;
import com.example.imius.adapter.FavoriteSongAdapter;
import com.example.imius.adapter.HistorySongAdapter;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.FragmentHistoryBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.HistorySong;
import com.example.imius.repository.MusicRepository;
import com.example.imius.viewmodel.FavoriteSongViewModel;
import com.example.imius.viewmodel.HistorySongViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistorySongViewModel viewModel;
    private HistorySongAdapter adapter;


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initView();

        return view;
    }

    private void initView (){
        binding.fragmentLibraryHistoryRvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistorySongAdapter(this.getContext());
        binding.fragmentLibraryHistoryRvHistory.setAdapter(adapter);

        viewModel = new ViewModelProvider(getActivity()).get(HistorySongViewModel.class);
        viewModel.getHistorySongs().observe(getViewLifecycleOwner(), songs -> {
            adapter.setHistorySongs(songs);
        //    Toast.makeText(getContext(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<HistorySong> historySongs = adapter.getHistorySongs();
                HistorySong song = historySongs.get(position);


                if (direction == ItemTouchHelper.LEFT){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle(getString(R.string.title_dialog_delete));
                    builder.setMessage(getString(R.string.title_dialog_delete_message));
                    builder.setCancelable(false);

                    builder.setPositiveButton(getString(R.string.yes_dialog),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    deleteHistorySong(song.getIdSong());
                                }
                            });

                    builder.setNegativeButton(getString(R.string.no_dialog),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    viewModel.refreshLiveData();
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        }).attachToRecyclerView(binding.fragmentLibraryHistoryRvHistory);
    }

    public void deleteHistorySong(int idSong) {
        MusicRepository repository = new MusicRepository();

        repository.deleteHistorySong(idSong).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getIsSuccess().equals(Constants.successfully)){
                    Toast.makeText(getContext(), getString(R.string.delete_success),Toast.LENGTH_SHORT).show();
                    viewModel.refreshLiveData();
                }else {
                    Toast.makeText(getContext(), getString(R.string.delete_failed), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshLiveData();
    }

}