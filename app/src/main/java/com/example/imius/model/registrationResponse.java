package com.example.imius.model;

import com.google.gson.annotations.SerializedName;

public class registrationResponse {
    // PHAN HOI DANG KY
    @SerializedName("success")
    private String success;
    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }
}
