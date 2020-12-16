
package com.app.knowyourism.Model.Restaurant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("locations")
    @Expose
    private List<Location2> locations = null;
    @SerializedName("_queryTime")
    @Expose
    private Integer queryTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Location2> getLocations() {
        return locations;
    }

    public void setLocations(List<Location2> locations) {
        this.locations = locations;
    }

    public Integer getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }

}
