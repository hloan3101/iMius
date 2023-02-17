package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class charts implements Serializable {
    // BANG XEP HANG AM NHAC
    @SerializedName("idCharts")
    @Expose
    private String idCharts;
    @SerializedName("nameCharts")
    @Expose
    private String nameCharts;
    @SerializedName("imgCharts")
    @Expose
    private String imgCharts;

    public String getIdCharts() {
        return idCharts;
    }

    public void setIdCharts(String idCharts) {
        this.idCharts = idCharts;
    }

    public String getNameCharts() {
        return nameCharts;
    }

    public void setNameCharts(String nameCharts) {
        this.nameCharts = nameCharts;
    }

    public String getImgCharts() {
        return imgCharts;
    }

    public void setImgCharts(String imgCharts) {
        this.imgCharts = imgCharts;
    }
}
