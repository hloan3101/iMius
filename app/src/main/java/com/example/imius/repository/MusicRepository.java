package com.example.imius.repository;

import com.example.imius.api.API;
import com.example.imius.data.DataLocalManager;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicRepository {
    private DataService dataService;

    public MusicRepository() {
        this.dataService = API.getAccount().create(DataService.class);
    }

    public RefreshLiveData<List<LibraryPlaylist>> getAllLibraryPlaylist(){
        RefreshLiveData<List<LibraryPlaylist>> data = new RefreshLiveData<>(callback -> {
            dataService.getLibraryPlaylistList(DataLocalManager.getUsernameData()).enqueue(new Callback<List<LibraryPlaylist>>() {
                @Override
                public void onResponse(Call<List<LibraryPlaylist>> call, Response<List<LibraryPlaylist>> response) {
                    callback.onDataLoaded((ArrayList<LibraryPlaylist>) response.body());
                }

                @Override
                public void onFailure(Call<List<LibraryPlaylist>> call, Throwable t) {

                }
            });
        });
        return data;
    }
}
