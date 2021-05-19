package com.app.knowyourism.Model.Feed;

import com.app.knowyourism.Model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {
    @SerializedName("visibilityRules")
    @Expose
    private List<String> visibilityRules = null;
    @SerializedName("answeringRules")
    @Expose
    private List<String> answeringRules = null;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("userId")
    @Expose
    private User user;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<String> getVisibilityRules() {
        return visibilityRules;
    }

    public void setVisibilityRules(List<String> visibilityRules) {
        this.visibilityRules = visibilityRules;
    }

    public List<String> getAnsweringRules() {
        return answeringRules;
    }

    public void setAnsweringRules(List<String> answeringRules) {
        this.answeringRules = answeringRules;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
