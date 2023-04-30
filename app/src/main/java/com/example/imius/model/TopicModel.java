package com.example.imius.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TopicModel implements Serializable {
    @SerializedName("idTopic")
    @Expose
    private int idTopic;
    @SerializedName("nameTopic")
    @Expose
    private String nameTopic;
    @SerializedName("imageTopic")
    @Expose
    private String imageTopic;

    public TopicModel(int idTopic, String nameTopic, String imageTopic) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
        this.imageTopic = imageTopic;
    }

    public TopicModel(String nameTopic, String imageTopic) {
        this.nameTopic = nameTopic;
        this.imageTopic = imageTopic;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getImageTopic() {
        return imageTopic;
    }

    public void setImageTopic(String imageTopic) {
        this.imageTopic = imageTopic;
    }
}
