package com.example.imius.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imius.R;
import com.example.imius.adapter.FavoriteSongAdapter;
import com.example.imius.adapter.LibraryPlaylistAdapter;
import com.example.imius.adapter.SongLibraryPlaylistAdapter;
import com.example.imius.constants.Constants;
import com.example.imius.data.DataLocalManager;
import com.example.imius.databinding.FragmentFavoriteBinding;
import com.example.imius.databinding.FragmentLibraryPlaylistBinding;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.repository.MusicRepository;
import com.example.imius.viewmodel.FavoriteSongViewModel;
import com.example.imius.viewmodel.LibraryPlaylistViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {


    private FragmentFavoriteBinding binding;
    private FavoriteSongViewModel viewModel;
    private FavoriteSongAdapter adapter;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initView();

        return view;
    }

    private void initView (){
        binding.fragmentLibraryFavoriteRvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteSongAdapter(this.getContext());
        binding.fragmentLibraryFavoriteRvFavorite.setAdapter(adapter);

        viewModel = new ViewModelProvider(getActivity()).get(FavoriteSongViewModel.class);
        viewModel.getFavoriteSongs().observe(getViewLifecycleOwner(), songs -> {
            adapter.setFavoriteSongs(songs);
            //  Toast.makeText(getContext(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
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
                List<FavoriteSong> favoriteSongs = adapter.getFavoriteSongs();
                FavoriteSong song = favoriteSongs.get(position);


                if (direction == ItemTouchHelper.LEFT){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle(getString(R.string.title_dialog_delete));
                    builder.setMessage(getString(R.string.title_dialog_delete_message));
                    builder.setCancelable(false);

                    builder.setPositiveButton(getString(R.string.yes_dialog),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    MusicRepository repository = new MusicRepository();

                                    repository.updateLikeOfNumber(song.getIdSong())
                                            .enqueue(new Callback<BaseResponse>() {
                                                @Override
                                                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                                    if (response.body() != null){
                                                        //    Toast.makeText(context, response.body().getIsSuccess(), Toast.LENGTH_LONG).show();
                                                        if (response.body().getIsSuccess().equals(Constants.successfully)){
                                                            if (response.body().getMessage().equals(Constants.DELETE)){
                                                                deleteLikeSong(song.getIdSong());
                                                            }
                                                        }
                                                    }
                                                }
                                                @Override
                                                public void onFailure(Call<BaseResponse> call, Throwable t) {
                                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
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
        }).attachToRecyclerView(binding.fragmentLibraryFavoriteRvFavorite);
    }

    public void deleteLikeSong(int idSong) {
        MusicRepository repository = new MusicRepository();
        //       repository.deleteLikeSong(DataLocalManager.getUsernameData(), idSong);

        repository.deleteLikeSong(DataLocalManager.getUsernameData(), idSong).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getIsSuccess().equals(Constants.successfully)){
                    Toast.makeText(getContext(), getString(R.string.library_playlist_delete_success),Toast.LENGTH_SHORT).show();
                    viewModel.refreshLiveData();
                }else {
                    Toast.makeText(getContext(), getString(R.string.library_playlist_delete_failed), Toast.LENGTH_SHORT).show();
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