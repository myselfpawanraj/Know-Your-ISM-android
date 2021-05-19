package com.app.knowyourism.Model;

import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("userId")
    private final String userId;

    @SerializedName("otp")
    private final long otp;

    public LoginBody(String userID, long otp) {
        this.userId = userID;
        this.otp = otp;
    }
}