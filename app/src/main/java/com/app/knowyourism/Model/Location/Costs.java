
package com.app.knowyourism.Model.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Costs {

    @SerializedName("rickshaw")
    @Expose
    private String rickshaw;
    @SerializedName("spending")
    @Expose
    private String spending;

    public String getRickshaw() {
        return rickshaw;
    }

    public void setRickshaw(String rickshaw) {
        this.rickshaw = rickshaw;
    }

    public String getSpending() {
        return spending;
    }

    public void setSpending(String spending) {
        this.spending = spending;
    }

}
