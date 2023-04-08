package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.repository.MusicRepository;

import java.util.List;

import retrofit2.Call;


public class LibraryPlaylistViewModel extends AndroidViewModel {

    private MusicRepository repository;
    private RefreshLiveData<List<LibraryPlaylist>> liveData;

    public LibraryPlaylistViewModel(@NonNull Application application) {
        super(application);

        repository = new MusicRepository();
        liveData = repository.getAllLibraryPlaylist();
    }

    public LiveData<List<LibraryPlaylist>> getListLibraryPlaylist (){
        return liveData;
    }

    public Call<BaseResponse> insertLibraryPlaylist (String nameLibraryPlaylist){
        return repository.insertLibraryPlaylist(nameLibraryPlaylist);

    }

    public void refreshLiveData (){
        liveData.refresh();
    }
}
