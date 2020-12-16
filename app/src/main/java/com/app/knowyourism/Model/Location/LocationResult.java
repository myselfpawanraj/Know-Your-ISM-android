
package com.app.knowyourism.Model.Location;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResult {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("_queryTime")
    @Expose
    private Integer queryTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Integer getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }

}
