package cn.mobiledaily.web.pojo;

import java.util.Date;

public class EventSchedulePOJO {
    private String name;
    private String description;
    private String place;
    private Date date;

    //<editor-fold desc="fields">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //</editor-fold>
}
