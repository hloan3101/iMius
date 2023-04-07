package com.example.imius.service;

import com.example.imius.model.PlaylistLibrary;
import com.example.imius.model.Singer;
import com.example.imius.model.Song;
import com.example.imius.model.TopicModel;
import com.example.imius.model.Trending;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {
    @POST("getTrendingList.php")
    Call<List<Trending>> trending();

    @POST("getSingerList.php")
    Call<List<Singer>> singer();

    @POST("getTopicList.php")
    Call<ArrayList<TopicModel>> topic();

    @POST("getLibraryPlaylistList.php")
    Call<List<PlaylistLibrary>> getLibraryPlaylistList(@Query("username") String username);

    @GET("findSong.php")
    Call<List<Song>> findSong(@Query("key") String key);


}
