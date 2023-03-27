package com.example.imius.repository;

import com.example.imius.api.API;
import com.example.imius.data.DataLocalManager;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.PlaylistLibrary;
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

    public RefreshLiveData<List<PlaylistLibrary>> getAllPlaylistLibrary (){
        RefreshLiveData<List<PlaylistLibrary>> data = new RefreshLiveData<>(callback -> {
            dataService.getLibraryPlaylistList(DataLocalManager.getUsernameData()).enqueue(new Callback<List<PlaylistLibrary>>() {
                @Override
                public void onResponse(Call<List<PlaylistLibrary>> call, Response<List<PlaylistLibrary>> response) {
                    callback.onDataLoaded((ArrayList<PlaylistLibrary>) response.body());
                }

                @Override
                public void onFailure(Call<List<PlaylistLibrary>> call, Throwable t) {

                }
            });
        });
        return data;
    }
}
