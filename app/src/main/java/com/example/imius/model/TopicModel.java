package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicModel {
    @SerializedName("IdTopic")
    @Expose
    private String idTopic;
    @SerializedName("NameTopic")
    @Expose
    private String nameTopic;
    @SerializedName("imgTopic")
    @Expose
    private String imgTopic;

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getImgTopic() {
        return imgTopic;
    }

    public void setImgTopic(String imgTopic) {
        this.imgTopic = imgTopic;
    }
}
