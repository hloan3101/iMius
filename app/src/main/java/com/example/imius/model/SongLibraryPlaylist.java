package com.example.imius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SongLibraryPlaylist{

    private int idSongLibraryPlaylist;
    private int idLibraryPlaylist;
    private int idSong;
    private String nameSong;
    private String nameSinger;
    private String 	imageSong;
    private String linkSong;

    public SongLibraryPlaylist(int idSongLibraryPlaylist, int idLibraryPlaylist, int idSong,
                               String nameSong, String nameSinger, String imageSong, String linkSong) {

        this.idSongLibraryPlaylist = idSongLibraryPlaylist;
        this.idLibraryPlaylist = idLibraryPlaylist;
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.imageSong = imageSong;
        this.linkSong = linkSong;
    }

    public int getIdSongLibraryPlaylist() {
        return idSongLibraryPlaylist;
    }

    public void setIdSongLibraryPlaylist(int idSongLibraryPlaylist) {
        this.idSongLibraryPlaylist = idSongLibraryPlaylist;
    }

    public int getIdLibraryPlaylist() {
        return idLibraryPlaylist;
    }

    public void setIdLibraryPlaylist(int idLibraryPlaylist) {
        this.idLibraryPlaylist = idLibraryPlaylist;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }
}
