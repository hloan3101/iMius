package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.Song;
import com.example.imius.repository.MusicRepository;

import java.util.List;

import retrofit2.Call;

public class SongViewModel extends AndroidViewModel {
    private MusicRepository musicRepository;
    private RefreshLiveData<List<Song>> songs;

    public SongViewModel(@NonNull Application application) {
        super(application);

        musicRepository = new MusicRepository();
//        songs = musicRepository.findSong(Constants.KEY);
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    public Call<List<Song>> findSong(String key){
        return musicRepository.findSong(key);
    }

    public Call<BaseResponse> checkLikeSong(String username, int idSong){
        return musicRepository.checkLikeSong(username, idSong);
    }

    public Call<BaseResponse> deleteLikeSong(String username, int idSong){
        return musicRepository.deleteLikeSong(username, idSong);
    }

    public Call<BaseResponse> insertLoveSong(int idLike, String username, Song song){
        return musicRepository.insertLoveSong(idLike, username, song);
    }

    public void refreshLiveData (){
        songs.refresh();
    }
}
