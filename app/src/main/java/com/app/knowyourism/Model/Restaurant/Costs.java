
package com.app.knowyourism.Model.Restaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Costs {

    @SerializedName("spending")
    @Expose
    private Spending spending;
    @SerializedName("rickshaw")
    @Expose
    private Integer rickshaw;

    public Spending getSpending() {
        return spending;
    }

    public void setSpending(Spending spending) {
        this.spending = spending;
    }

    public Integer getRickshaw() {
        return rickshaw;
    }

    public void setRickshaw(Integer rickshaw) {
        this.rickshaw = rickshaw;
    }

}
