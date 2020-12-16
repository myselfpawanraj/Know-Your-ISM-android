
package com.app.knowyourism.Model.Restaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("ambience")
    @Expose
    private Integer ambience;
    @SerializedName("service")
    @Expose
    private Integer service;
    @SerializedName("food")
    @Expose
    private Integer food;

    public Integer getAmbience() {
        return ambience;
    }

    public void setAmbience(Integer ambience) {
        this.ambience = ambience;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

}
