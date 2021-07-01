package com.app.knowyourism.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    @SerializedName("clubs")
    private List<String> clubs = new ArrayList<>();
    @SerializedName("newUser")
    private Boolean newUser;
    @SerializedName("roles")
    private List<String> roles = new ArrayList<>();
    @SerializedName("reputation")
    private Integer reputation;
    @SerializedName("deleted")
    private Boolean deleted;
    @SerializedName("_id")
    private String id;
    @SerializedName("instituteEmail")
    private String instituteEmail;
    @SerializedName("name")
    private String name;
    @SerializedName("admissionNumber")
    private String admissionNumber;
    @SerializedName("department")
    private String department;
    @SerializedName("course")
    private String course;
    @SerializedName("personalEmail")
    private String personalEmail;
    @SerializedName("phone")
    private String phone;
    @SerializedName("photoURI")
    private String photoURI;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("__v")
    private Integer v;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List< String > getClubs() {
        return clubs;
    }

    public void setClubs(List< String > clubs) {
        this.clubs = clubs;
    }

    public Boolean getNewUser() {
        return newUser;
    }

    public void setNewUser(Boolean newUser) {
        this.newUser = newUser;
    }

    public List< String > getRoles() {
        return roles;
    }

    public void setRoles(List< String > roles) {
        this.roles = roles;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
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

    public String getInstituteEmail() {
        return instituteEmail;
    }

    public void setInstituteEmail(String instituteEmail) {
        this.instituteEmail = instituteEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
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
