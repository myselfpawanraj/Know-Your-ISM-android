package com.app.knowyourism.Model;

import com.google.gson.annotations.SerializedName;

public class OtpInitiateResponse {

    @SerializedName("userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}