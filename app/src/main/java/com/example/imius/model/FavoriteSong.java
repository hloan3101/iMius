package com.example.imius.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteSong{
    // BAI HAT YEU THICH

    private int idLike;
    private String username;
    private int idSong;
    private String nameSong;
    private String nameSinger;
    private String imageSong;
    private String linkSong;

    public FavoriteSong(int idLike, String username, int idSong, String nameSong,
                        String nameSinger, String imageSong, String linkSong) {
        this.idLike = idLike;
        this.username = username;
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.nameSinger = nameSinger;
        this.imageSong = imageSong;
        this.linkSong = linkSong;
    }

    public int getIdLike() {
        return idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
