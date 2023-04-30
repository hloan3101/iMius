package com.example.imius.repository;

import com.example.imius.api.API;
import com.example.imius.data.DataLocalManager;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.Song;
import com.example.imius.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

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

    public Call<BaseResponse> insertLibraryPlaylist (String nameLibraryPlaylist){
        return dataService.insertLibraryPlaylist(nameLibraryPlaylist, DataLocalManager.getUsernameData());
    }

    public Call<List<Song>> findSong(String key){
        return dataService.findSong(key);
    }

    public Call<BaseResponse> checkLikeSong(String username, int idSong){
        return dataService.checkLikeSong(username, idSong);
    }

    public Call<BaseResponse> deleteLikeSong(String username, int idSong){
        return dataService.deleteLikeSong(username, idSong);
    }

    public Call<BaseResponse> insertLoveSong(String username, Song song){
        return dataService.insertLoveSong(username, song.getIdSong(), song.getNameSong(),
                song.getNameSinger(), song.getImageSong(), song.getLinkSong());
    }

    public Call<BaseResponse> updateLikeOfNumber (int idSong){
        return dataService.updateLikeOfNumber(DataLocalManager.getUsernameData(), idSong);
    }
}
