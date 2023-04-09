package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imius.data.DataLocalManager;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.FavoriteSong;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.SongLibraryPlaylist;
import com.example.imius.repository.LibraryRepository;

import java.util.List;

import retrofit2.Call;


public class LibraryPlaylistViewModel extends AndroidViewModel {

    private LibraryRepository repository;
    private RefreshLiveData<List<LibraryPlaylist>> libraryPlaylistList;
    private RefreshLiveData<List<FavoriteSong>> favoriteSongList;

    public LibraryPlaylistViewModel(@NonNull Application application) {
        super(application);

        repository = new LibraryRepository();
        libraryPlaylistList = repository.getAllLibraryPlaylist();
        favoriteSongList = repository.getAllFavoriteSong();
    }

    public LiveData<List<LibraryPlaylist>> getListLibraryPlaylist (){
        return libraryPlaylistList;
    }

    public RefreshLiveData<List<FavoriteSong>> getFavoriteSongList() {
        return favoriteSongList;
    }
    public RefreshLiveData<List<SongLibraryPlaylist>> getAllSongLibraryPlaylist(int idLibraryPlaylist) {
        return repository.getAllSongLibraryPlaylist(idLibraryPlaylist);
    }

    public Call<BaseResponse> insertLibraryPlaylist (String nameLibraryPlaylist){
        return repository.insertLibraryPlaylist(nameLibraryPlaylist);

    }

    public Call<BaseResponse> deleteLibraryPlaylist (int idLibraryPlaylist){
        return repository.deleteLibraryPlaylist(idLibraryPlaylist);

    }

    public Call<BaseResponse> deleteFavoriteSong (String idSong){
        return repository.deleteFavoriteSong(idSong);
    }

    public Call<BaseResponse> updateLibraryPlaylistName (String nameLibraryPlaylist, String newNameLibraryPlaylist){
        return repository.updateLibraryPlaylistName(nameLibraryPlaylist, newNameLibraryPlaylist);
    }

    public void refreshLiveData (){
        libraryPlaylistList.refresh();
    }
}
