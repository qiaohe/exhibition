package cn.mobiledaily.domain.mobile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CheckInEntry implements Serializable {
    private static final long serialVersionUID = 3891325785188793952L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;

    @ManyToOne
    private Attendee attendee;
    @Embedded
    private Location location;
    @Temporal(TemporalType.TIMESTAMP)
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    //</editor-fold>
}
