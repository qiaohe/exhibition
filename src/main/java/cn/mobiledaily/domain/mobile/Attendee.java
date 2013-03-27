package cn.mobiledaily.domain.mobile;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Attendee implements Serializable, ExhibitionContent {
    private static final String COLLECTION_SUFFIX = ".attendee";
    private String id;
    private Date registerDate;
    private String serviceToken;
    private MobilePlatform mobilePlatform;
    @DBRef
    private Exhibition exhibition;
    private List<CheckInEntry> checkInHistories = new ArrayList<>();
    private Location location;
    private int distance;
    private Date checkInAt;

    public Attendee() {
        registerDate = new Date();
    }

    public Attendee(String serviceToken, Exhibition exhibition) {
        this();
        this.serviceToken = serviceToken;
        this.exhibition = exhibition;
    }

    public Attendee(String serviceToken, Exhibition exhibition, MobilePlatform mobilePlatform) {
        this(serviceToken, exhibition);
        this.mobilePlatform = mobilePlatform;
    }

    @Override
    public String getCollectionSuffix() {
        return COLLECTION_SUFFIX;
    }

    public void addCheckInHistory(CheckInEntry entry) {
        if (!checkInHistories.contains(entry)) {
            checkInHistories.add(entry);
            entry.setAttendee(this);
        }
    }

    public void removeCheckInHistory(CheckInEntry entry) {
        if (checkInHistories.contains(entry))
            checkInHistories.remove(entry);
    }

    //<editor-fold desc="fields">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public MobilePlatform getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(MobilePlatform mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }

    public Date getCheckInAt() {
        return checkInAt;
    }

    public void setCheckInAt(Date checkInAt) {
        this.checkInAt = checkInAt;
    }
    //</editor-fold>
}
