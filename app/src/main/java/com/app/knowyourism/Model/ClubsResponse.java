package com.app.knowyourism.Model;

import com.app.knowyourism.Model.LnF.LostFound;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClubsResponse {
    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("_queryTime")
    @Expose
    private Integer queryTime;
    @SerializedName("records")
    @Expose
    private List< Club > records = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }

    public List<Club> getRecords() {
        return records;
    }

    public void setRecords(List<Club> records) {
        this.records = records;
    }
}
