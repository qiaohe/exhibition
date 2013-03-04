package cn.mobiledaily.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventSchedule {
    private Long id;
    private String name;
    private String description;
    private String date;
    private String place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
