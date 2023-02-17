package com.example.imius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class song implements Parcelable {
    // BAI HAT
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

    public song(int idSong, String nameSong, String imgSong, String nameSinger, String linkSong) {
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.nameSinger = nameSinger;
        this.linkSong = linkSong;
    }

    public song(Parcel in) {
        idSong = in.readInt();
        nameSong = in.readString();
        imgSong = in.readString();
        nameSinger = in.readString();
        linkSong = in.readString();
    }

    public static final Creator<song> CREATOR = new Creator<song>() {
        @Override
        public song createFromParcel(Parcel in) {
            return new song(in);
        }

        @Override
        public song[] newArray(int size) {
            return new song[size];
        }
    };

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getImgSong() {
        return imgSong;
    }

    public void setImgSong(String imgSong) {
        this.imgSong = imgSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    //Mo ta noi dung
    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(idSong);
        parcel.writeString(nameSong);
        parcel.writeString(imgSong);
        parcel.writeString(nameSinger);
        parcel.writeString(linkSong);
    }
}
