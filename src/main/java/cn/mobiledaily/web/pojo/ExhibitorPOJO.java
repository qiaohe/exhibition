package cn.mobiledaily.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExhibitorPOJO {
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String stand;
    private String company;
    @JsonIgnore
    private String email;
    private String address;
    private String website;
    @JsonIgnore
    private String category;
    private String location;
    private String phone;
    private String description;

    //<editor-fold desc="fields">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
}
