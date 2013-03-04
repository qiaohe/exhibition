package cn.mobiledaily.domain.mobile;

import cn.mobiledaily.domain.Exhibition;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Attendee implements Serializable {
    private static final long serialVersionUID = 4018851031616246794L;
    private String dummyUserName;
    private String serviceToken;
    private List<CheckInEntry> checkInHistories;

    private Exhibition exhibition;

    public String getDummyUserName() {
        return dummyUserName;
    }

    public void setDummyUserName(String dummyUserName) {
        this.dummyUserName = dummyUserName;
    }

    public List<CheckInEntry> getCheckInHistories() {
        return checkInHistories;
    }

    public void setCheckInHistories(List<CheckInEntry> checkInHistories) {
        this.checkInHistories = checkInHistories;
    }

    public String getServiceToken() {
        return serviceToken;
    }

    public void setServiceToken(String serviceToken) {
        this.serviceToken = serviceToken;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
}
