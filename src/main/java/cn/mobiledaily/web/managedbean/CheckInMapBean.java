package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.web.assembler.CheckInAssembler;
import cn.mobiledaily.web.common.SpringContext;
import cn.mobiledaily.web.pojo.AttendeePOJO;
import cn.mobiledaily.web.pojo.CheckIn;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name = "checkInMap")
@ViewScoped
public class CheckInMapBean implements Serializable {
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{attendeeService}")
    transient private AttendeeService attendeeService;
    @ManagedProperty("#{checkInAssembler}")
    transient private CheckInAssembler checkInAssembler;
    private Comparator<AttendeePOJO> attendeePOJOComparator = new Comparator<AttendeePOJO>() {
        @Override
        public int compare(AttendeePOJO o1, AttendeePOJO o2) {
            if (o1.getCheckInAt() != null) {
                return o2.getCheckInAt() == null ? 1 : -1 * o1.getCheckInAt().compareTo(o2.getCheckInAt());
            } else if (o2.getCheckInAt() != null) {
                return 1;
            } else {
                try {
                    return -1 * o1.getRegisterDate().compareTo(o2.getRegisterDate());
                } catch (Exception e) {
                    return 1;
                }
            }
        }
    };
    private Comparator<CheckInEntry> checkInEntryComparator = new Comparator<CheckInEntry>() {
        @Override
        public int compare(CheckInEntry o1, CheckInEntry o2) {
            try {
                return -1 * o1.getDate().compareTo(o2.getDate());
            } catch (Exception e) {
                return 1;
            }
        }
    };

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        attendeeService = SpringContext.getAttendeeService();
        checkInAssembler = SpringContext.getCheckInAssembler();
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setAttendeeService(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    public void setCheckInAssembler(CheckInAssembler checkInAssembler) {
        this.checkInAssembler = checkInAssembler;
    }

    public List<CheckIn> getCheckIns() {
        return checkInAssembler.toCheckIn(attendeeService.findAttendees(userBean.getExhibitionCode()));
    }

    public List<AttendeePOJO> getAttendees() {
        List<AttendeePOJO> list = checkInAssembler.toAttendees(attendeeService.findAttendees(userBean.getExhibitionCode()));
        Collections.sort(list, attendeePOJOComparator);
        for (AttendeePOJO pojo : list) {
            if (pojo.getCheckInHistories() != null) {
                Collections.sort(pojo.getCheckInHistories(), checkInEntryComparator);
            }
        }
        return list;
    }
}
