
package com.app.knowyourism.Model.Restaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spending {

    @SerializedName("for2")
    @Expose
    private Integer for2;
    @SerializedName("for4")
    @Expose
    private Integer for4;
    @SerializedName("for6")
    @Expose
    private Integer for6;

    public Integer getFor2() {
        return for2;
    }

    public void setFor2(Integer for2) {
        this.for2 = for2;
    }

    public Integer getFor4() {
        return for4;
    }

    public void setFor4(Integer for4) {
        this.for4 = for4;
    }

    public Integer getFor6() {
        return for6;
    }

    public void setFor6(Integer for6) {
        this.for6 = for6;
    }

}
