package cn.mobiledaily.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExhibitionPOJO {
    private Date updatedAt;
    private String name;
    private String description;
    private String address;
    private Date startDate;
    private Date endDate;
    private String organizer;
    @JsonIgnore
    private List<SponsorPOJO> sponsors = new ArrayList<>();
    private List<SpeakerPOJO> speakers = new ArrayList<>();
    private List<ExhibitorPOJO> exhibitors = new ArrayList<>();
    private List<EventSchedulePOJO> eventSchedules = new ArrayList<>();

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public List<SponsorPOJO> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<SponsorPOJO> sponsors) {
        this.sponsors = sponsors;
    }

    public List<SpeakerPOJO> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<SpeakerPOJO> speakers) {
        this.speakers = speakers;
    }

    public List<ExhibitorPOJO> getExhibitors() {
        return exhibitors;
    }

    public void setExhibitors(List<ExhibitorPOJO> exhibitors) {
        this.exhibitors = exhibitors;
    }

    public List<EventSchedulePOJO> getEventSchedules() {
        return eventSchedules;
    }

    public void setEventSchedules(List<EventSchedulePOJO> eventSchedules) {
        this.eventSchedules = eventSchedules;
    }
}
