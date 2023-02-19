package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trending implements Serializable {
    @SerializedName("idTrending")
    @Expose
    private String idTrending;
    @SerializedName("nameTrending")
    @Expose
    private String nameTrending;
    @SerializedName("imgTrending")
    @Expose
    private String imgTrending;

    public String getIdTrending() {
        return idTrending;
    }

    public void setIdTrending(String idTrending) {
        this.idTrending = idTrending;
    }

    public String getNameTrending() {
        return nameTrending;
    }

    public void setNameTrending(String nameTrending) {
        this.nameTrending = nameTrending;
    }

    public String getImgTrending() {
        return imgTrending;
    }

    public void setImgTrending(String imgTrending) {
        this.imgTrending = imgTrending;
    }
}
