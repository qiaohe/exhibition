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
    private Date createdAt;
    private String dummyUserName;
    private String serviceToken;
    @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckInEntry> checkInHistories = new ArrayList<>();
    @ManyToOne
    private Exhibition exhibition;

    @PrePersist
    void prePersist() {
        createdAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDummyUserName() {
        return dummyUserName;
    }

    public void setDummyUserName(String dummyUserName) {
        this.dummyUserName = dummyUserName;
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
