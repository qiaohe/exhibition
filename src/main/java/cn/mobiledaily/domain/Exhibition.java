package cn.mobiledaily.domain;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Exhibition {
    private Long id;
    private String name;
    private String description;
    private String splashScreen;
    private List<String> topBanner;
    private String address;
    private Date startDate;
    private Date endDate;
    private String type;
    private String organizer;
    private List<String> sponsors;

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

    public String getSplashScreen() {
        return splashScreen;
    }

    public void setSplashScreen(String splashScreen) {
        this.splashScreen = splashScreen;
    }

    public List<String> getTopBanner() {
        return topBanner;
    }

    public void setTopBanner(List<String> topBanner) {
        this.topBanner = topBanner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public List<String> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<String> sponsors) {
        this.sponsors = sponsors;
    }
}
