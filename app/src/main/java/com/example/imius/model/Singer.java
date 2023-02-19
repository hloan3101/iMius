package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Singer implements Serializable {
    @SerializedName("idSinger")
    @Expose
    private String idSinger;
    @SerializedName("nameSinger")
    @Expose
    private String nameSinger;
    @SerializedName("avatarSinger")
    @Expose
    private String avatarSinger;

    public String getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(String idSinger) {
        this.idSinger = idSinger;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getAvatarSinger() {
        return avatarSinger;
    }

    public void setAvatarSinger(String avatarSinger) {
        this.avatarSinger = avatarSinger;
    }
}
