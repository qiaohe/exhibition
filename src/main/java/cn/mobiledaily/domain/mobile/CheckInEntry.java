package cn.mobiledaily.domain.mobile;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;

public class CheckInEntry implements Serializable {
    private String id;
    @Transient
    private Attendee attendee;
    private Location location;
    private Date date;

    public CheckInEntry() {
        this(null, new Date());
    }

    public CheckInEntry(Location location) {
        this(location, new Date());
    }

    public CheckInEntry(Location location, Date date) {
        this.location = location;
        this.date = date;
    }

    //<editor-fold desc="fields">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //</editor-fold>
}
