package com.example.imius.service;

import com.example.imius.model.BaseResponse;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.Singer;
import com.example.imius.model.Song;
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

    @GET("insertLibraryPlaylist.php")
    Call<BaseResponse> insertLibraryPlaylist (@Query("nameLibraryPlaylist") String nameLibraryPlaylist,
                                              @Query("username") String username);

    @GET("findSong.php")
    Call<List<Song>> findSong(@Query("key") String key);

    @POST("checkLikeSong.php")
    Call<BaseResponse> checkLikeSong(@Query("username") String username, @Query("idSong") int idSong);

    @POST("deleteLikeSong.php")
    Call<BaseResponse> deleteLikeSong(@Query("username") String username, @Query("idSong")int idSong);

    @POST("insertLikeSong.php")
    Call<BaseResponse> insertLoveSong(@Query("idLike") int idLike, @Query("username") String username,
                                      @Query("idSong")int idSong, @Query("nameSong") String nameSong,
                                      @Query("nameSinger") String nameSinger, @Query("imageSong") String imageSong,
                                      @Query("linkSong") String linkSong);

}
