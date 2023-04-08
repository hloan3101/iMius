package com.example.imius.service;

import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.Singer;
import com.example.imius.model.TopicModel;
import com.example.imius.model.Trending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {

    @POST("getLibraryPlaylistList.php")
    Call<List<LibraryPlaylist>> getLibraryPlaylistList(@Query("username") String username);

    @POST("getTrendingList.php")
    Call<List<Trending>> getTrending();

    @POST("getTopicList.php")
    Call<List<TopicModel>> getTopic();

    @GET("getSingerList.php")
    Call<List<Singer>> getSinger();

}
