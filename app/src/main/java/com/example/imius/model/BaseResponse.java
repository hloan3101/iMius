package com.example.imius.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    // DAP TRA

    private String isSuccess;
    private String message;

    public String getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
