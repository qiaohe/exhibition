package cn.mobiledaily.domain.mobile;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CheckInEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Attendee attendee;
    @OneToOne(mappedBy = "checkInEntry", cascade = CascadeType.ALL, optional = true)
    private Location location;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public CheckInEntry() {
    }

    public CheckInEntry(Location location, Date date) {
        this.location = location;
        this.date = date;
    }

    //<editor-fold desc="fields">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
