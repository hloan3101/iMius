package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartsModel {
    // BANG XEP HANG AM NHAC
    private int idChart;
    private String nameChart;
    private String imageChart;

    public ChartsModel(int idChart, String nameChart, String imageChart) {
        this.idChart = idChart;
        this.nameChart = nameChart;
        this.imageChart = imageChart;
    }

    public int getIdChart() {
        return idChart;
    }

    public void setIdChart(int idChart) {
        this.idChart = idChart;
    }

    public String getNameChart() {
        return nameChart;
    }

    public void setNameChart(String nameChart) {
        this.nameChart = nameChart;
    }

    public String getImageChart() {
        return imageChart;
    }

    public void setImageChart(String imageChart) {
        this.imageChart = imageChart;
    }
}
