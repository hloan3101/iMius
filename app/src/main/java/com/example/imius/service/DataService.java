package com.example.imius.service;

import com.example.imius.model.PlaylistLibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {

    @POST("getLibraryPlaylistList.php")
    Call<List<PlaylistLibrary>> getLibraryPlaylistList(@Query("username") String username);
}
