package com.example.imius.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class songLibraryPlaylist implements Parcelable {
    @SerializedName("idSongLibraryPlaylist")
    @Expose
    private int idSongLibraryPlaylist;
    @SerializedName("idLibraryPlaylist")
    @Expose
    private int idLibraryPlaylist;
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

    public songLibraryPlaylist(int idSongLibraryPlaylist, int idLibraryPlaylist, int idSong,
                               String nameSong, String imgSong, String nameSinger, String linkSong) {
        this.idSongLibraryPlaylist = idSongLibraryPlaylist;
        this.idLibraryPlaylist = idLibraryPlaylist;
        this.idSong = idSong;
        this.nameSong = nameSong;
        this.imgSong = imgSong;
        this.nameSinger = nameSinger;
        this.linkSong = linkSong;
    }

    protected songLibraryPlaylist(Parcel in) {
        idSongLibraryPlaylist = in.readInt();
        idLibraryPlaylist = in.readInt();
        idSong = in.readInt();
        nameSong = in.readString();
        imgSong = in.readString();
        nameSinger = in.readString();
        linkSong = in.readString();
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

    public static final Creator<songLibraryPlaylist> CREATOR = new Creator<songLibraryPlaylist>() {
        @Override
        public songLibraryPlaylist createFromParcel(Parcel in) {
            return new songLibraryPlaylist(in);
        }

        @Override
        public songLibraryPlaylist[] newArray(int size) {
            return new songLibraryPlaylist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idSongLibraryPlaylist);
        dest.writeInt(idLibraryPlaylist);
        dest.writeInt(idSong);
        dest.writeString(nameSong);
        dest.writeString(imgSong);
        dest.writeString(nameSinger);
        dest.writeString(linkSong);
    }
}