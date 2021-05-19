package com.app.knowyourism.Model.LnF;

import com.google.gson.annotations.SerializedName;

public class LostFound {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String details;
    @SerializedName("imageUrl")
    private String url;
    @SerializedName("found")
    private boolean found;
    @SerializedName("updatedAt")
    private String date;
    @SerializedName("userId")
    private String user;

    public LostFound(String title, String details, boolean found, String user) {
        this.title = title;
        this.details = details;
        this.found = found;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
