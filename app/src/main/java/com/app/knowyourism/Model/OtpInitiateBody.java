package com.app.knowyourism.Model;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpInitiateBody {

    static final String CLINT_ID = "609ea9c40fba5643342abc86";
    static final String CLINT_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MDllYTljNDBmYmE1NjQzMzQyYWJjODYiLCJpc3MiOiJLbm93IFlvdXIgSVNNIiwidXNyIjoiNWVkN2E3MzI4ODY0YjU0YzJhMGUyYzQyIiwidXJpIjoiaHR0cDovL2dvb2dsZS5jb20vb2F1dGgvcmVkaXJlY3QiLCJpYXQiOjE2MjEwMTA4ODQsImV4cCI6MjQ4NTAxMDg4NH0.0OhlTj9zn1ug92CD_Z3yIiJTBH3VPq4KzApD5Yje6p4";

    @SerializedName("clientId")
    private final String clientId;

    @SerializedName("clientSecret")
    private final String clientSecret;

    @SerializedName("username")
    private final String username;

    @SerializedName("usernameType")
    private final String usernameType;

    public OtpInitiateBody(String username, String usernameType) {
        this.clientId = CLINT_ID;
        this.clientSecret = CLINT_SECRET;
        this.username = username;
        this.usernameType = usernameType;
    }
}