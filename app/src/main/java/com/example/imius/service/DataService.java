package com.example.imius.service;

import com.example.imius.model.ChartsModel;
import com.example.imius.model.BaseResponse;
import com.example.imius.model.LibraryPlaylist;
import com.example.imius.model.NewReleaseModel;
import com.example.imius.model.Singer;
import com.example.imius.model.TopicModel;
import com.example.imius.model.Trending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {

    @GET("getLibraryPlaylistList.php")
    Call<List<LibraryPlaylist>> getLibraryPlaylistList(@Query("username") String username);

    @POST("getTrendingList.php")
    Call<List<Trending>> getTrending();

    @POST("getTopicList.php")
    Call<List<TopicModel>> getTopic();

    @GET("getSingerList.php")
    Call<List<Singer>> getSinger();

    @POST("getNewReleaseList.php")
    Call<List<NewReleaseModel>> getNewRelease();

    @POST("getChartList.php")
    Call<List<ChartsModel>> getCharts();
    
    @GET("insertLibraryPlaylist.php")
    Call<BaseResponse> insertLibraryPlaylist (@Query("nameLibraryPlaylist") String nameLibraryPlaylist,
                                              @Query("username") String username);

    @GET("deleteLibraryPlaylist.php")
    Call<BaseResponse> deleteLibraryPlaylist (@Query("idLibraryPlaylist") int idLibraryPlaylist);

    @GET("updateLibraryPlaylistName.php")
    Call<BaseResponse> updateLibraryPlaylistName (@Query("nameLibraryPlaylist") String nameLibraryPlaylist,
                                                  @Query("newNameLibraryPlaylist") String newNameLibraryPlaylist,
                                                  @Query("username") String username);
}
