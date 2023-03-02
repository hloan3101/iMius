package com.example.imius.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteSong implements Parcelable {
    // BAI HAT YEU THICH
    @SerializedName("idFavorite")
    @Expose
    private int idFavorite;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("idSong")
    @Expose
    private int idSong;

    @SerializedName("nameSong")
    @Expose
    private String nameSong;

    @SerializedName("imgSong")
    @Expose
    private String imgSong;

    @SerializedName("nameSinger")
    @Expose
    private String nameSinger;

    @SerializedName("linkSong")
    @Expose
    private String linkSong;

    public FavoriteSong(int idFavorite, String userName, int idSong, String nameSong,
                        String imgSong, String nameSinger, String linkSong){
        this.idFavorite = idFavorite;
        this.userName = userName;
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.nameSinger = nameSinger;
        this.linkSong = linkSong;
    }

    public int getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(int idFavorite) {
        this.idFavorite = idFavorite;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getImgSong() {
        return imgSong;
    }

    public void setImgSong(String imgSong) {
        this.imgSong = imgSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    protected FavoriteSong(Parcel in){
        idFavorite = in.readInt();
        userName = in.readString();
        idSong = in.readInt();
        nameSong = in.readString();
        imgSong = in.readString();
        nameSinger = in.readString();
        linkSong = in.readString();
    }

    public static final Creator<FavoriteSong> CREATOR = new Creator<FavoriteSong>() {
        @Override
        public FavoriteSong createFromParcel(Parcel in) {
            return new FavoriteSong(in);
        }

        @Override
        public FavoriteSong[] newArray(int size) {
            return new FavoriteSong[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }



}
