package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.PlaylistLibrary;
import com.example.imius.repository.MusicRepository;

import java.util.List;


public class PlaylistLibraryViewModel extends AndroidViewModel {

    private MusicRepository repository;
    private RefreshLiveData<List<PlaylistLibrary>> liveData;

    public PlaylistLibraryViewModel(@NonNull Application application) {
        super(application);

        repository = new MusicRepository();
        liveData = repository.getAllPlaylistLibrary();
    }

    public void refreshLiveData (){
        liveData.refresh();
    }
}
