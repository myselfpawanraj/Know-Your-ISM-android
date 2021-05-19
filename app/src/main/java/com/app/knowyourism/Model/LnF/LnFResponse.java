package com.app.knowyourism.Model.LnF;

import com.app.knowyourism.Model.Feed.Feed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LnFResponse {
    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("_queryTime")
    @Expose
    private Integer queryTime;
    @SerializedName("records")
    @Expose
    private List<LostFound> records = null;

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

    public List<LostFound> getRecords() {
        return records;
    }

    public void setRecords(List<LostFound> records) {
        this.records = records;
    }
}
