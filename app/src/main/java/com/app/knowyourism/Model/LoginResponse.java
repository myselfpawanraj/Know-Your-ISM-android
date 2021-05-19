package com.app.knowyourism.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("accessToken")
    String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
