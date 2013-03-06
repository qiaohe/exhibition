package cn.mobiledaily.domain.mobile;

import cn.mobiledaily.domain.Exhibition;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Attendee implements Serializable {
    private static final long serialVersionUID = 4018851031616246794L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    private String serviceToken;
    @ManyToOne
    private Exhibition exhibition;
    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckInEntry> checkInHistories = new ArrayList<>();

    public Attendee() {
        registerDate = new Date();
    }

    public Attendee(String serviceToken, Exhibition exhibition) {
        this();
        this.serviceToken = serviceToken;
        this.exhibition = exhibition;
    }

    public void addCheckInHistory(CheckInEntry entry) {
        if (!checkInHistories.contains(entry))
            checkInHistories.add(entry);
    }

    public void removeCheckInHistory(CheckInEntry entry) {
        if (checkInHistories.contains(entry))
            checkInHistories.remove(entry);
    }


    //<editor-fold desc="fields">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getDummyUserName() {
        return "v" + id;
    }


    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    public List<CheckInEntry> getCheckInHistories() {
        return checkInHistories;
    }

    public void setCheckInHistories(List<CheckInEntry> checkInHistories) {
        this.checkInHistories = checkInHistories;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
    //</editor-fold>
}
