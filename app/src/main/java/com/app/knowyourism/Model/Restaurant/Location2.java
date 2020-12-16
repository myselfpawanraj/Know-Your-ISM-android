
package com.app.knowyourism.Model.Restaurant;

import com.app.knowyourism.Model.Location.Coordinates;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location2 {

    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("costs")
    @Expose
    private Costs costs;
    @SerializedName("propertyType")
    @Expose
    private String propertyType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("events")
    @Expose
    private String events;
    @SerializedName("alcohol")
    @Expose
    private String alcohol;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
