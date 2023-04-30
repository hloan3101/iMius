package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {
    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;

    @SerializedName("namePlaylist")
    @Expose
    private String namePlaylist;

    @SerializedName("imagePlaylist")
    @Expose
    private String imagePlaylist;

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getName() {
        return namePlaylist;
    }

    public void setName(String name) {
        this.namePlaylist = namePlaylist;
    }

    public void setImagePlaylist(String imagePlaylist) {
        this.imagePlaylist = imagePlaylist;
    }

    public String getImagePlaylist() {
        return imagePlaylist;
    }
}
