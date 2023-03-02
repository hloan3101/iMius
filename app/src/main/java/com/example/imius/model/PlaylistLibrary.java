package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaylistLibrary implements Serializable {
    @SerializedName("idplaylistLibrary")
    @Expose
    private int idplaylistLibrary;

    @SerializedName("namePlaylistLibrary")
    @Expose
    private String namePlaylistLibrary;

    @SerializedName("imgPlaylistLibrary")
    @Expose
    private String imgPlaylistLibrary;

    @SerializedName("userName")
    @Expose
    private String userName;

    public int getIdplaylistLibrary() {
        return idplaylistLibrary;
    }

    public void setIdplaylistLibrary(int idplaylistLibrary) {
        this.idplaylistLibrary = idplaylistLibrary;
    }

    public String getNamePlaylistLibrary() {
        return namePlaylistLibrary;
    }

    public void setNamePlaylistLibrary(String namePlaylistLibrary) {
        this.namePlaylistLibrary = namePlaylistLibrary;
    }

    public String getImgPlaylistLibrary() {
        return imgPlaylistLibrary;
    }

    public void setImgPlaylistLibrary(String imgPlaylistLibrary) {
        this.imgPlaylistLibrary = imgPlaylistLibrary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
