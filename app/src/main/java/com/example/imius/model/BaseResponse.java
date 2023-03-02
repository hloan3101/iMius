package com.example.imius.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    // DAP TRA

    private String success;
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
