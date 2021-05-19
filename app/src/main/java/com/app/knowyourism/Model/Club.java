package com.app.knowyourism.Model;

import com.google.gson.annotations.SerializedName;

public class Club {
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("photo")
    String photoUrl;
    @SerializedName("facebook")
    String facebook;
    @SerializedName("instagram")
    String ig;

    public Club(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getIg() {
        return ig;
    }

    public void setIg(String ig) {
        this.ig = ig;
    }
}
