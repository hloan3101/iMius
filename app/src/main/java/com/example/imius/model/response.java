package com.example.imius.model;

import com.google.gson.annotations.SerializedName;

public class response {
    // DAP TRA
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
