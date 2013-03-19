package cn.mobiledaily.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SpeakerPOJO {
    private String name;
    private String profile;
    @JsonIgnore
    private String email;
    private String position;
    private String company;
    @JsonIgnore
    private String mobilePhone;
    private String photo;

    //<editor-fold desc="fields">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //</editor-fold>
}
