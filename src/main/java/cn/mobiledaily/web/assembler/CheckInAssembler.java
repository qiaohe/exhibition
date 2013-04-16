package cn.mobiledaily.web.assembler;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.web.pojo.AttendeePOJO;
import cn.mobiledaily.web.pojo.CheckIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CheckInAssembler {
    private Logger logger = LoggerFactory.getLogger(CheckInAssembler.class);
    public List<CheckIn> toCheckIn(Collection<Attendee> attendees) {
        List<CheckIn> list = new ArrayList<>(attendees.size());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:mm");
        for (Attendee attendee : attendees) {
            if (attendee.getLocation() != null) {
                list.add(new CheckIn(String.valueOf(attendee.getId()),
                        attendee.getLocation().getLatitude(),
                        attendee.getLocation().getLongitude(),
                        attendee.getLocation().getAddress(),
                        dateFormat.format(attendee.getCheckInAt())));
            }
        }
        return list;
    }

    public CheckIn toCheckIn(Attendee attendee) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:mm");
        return new CheckIn(String.valueOf(attendee.getId()),
                attendee.getLocation().getLatitude(),
                attendee.getLocation().getLongitude(),
                attendee.getLocation().getAddress(),
                dateFormat.format(attendee.getCheckInAt()));
    }

    public List<AttendeePOJO> toAttendees(Collection<Attendee> attendees) {
        List<AttendeePOJO> list = new ArrayList<>(attendees.size());
        for (Attendee attendee : attendees) {
            AttendeePOJO pojo = new AttendeePOJO();
            pojo.setId(attendee.getId());
            pojo.setRegisterDate(attendee.getRegisterDate());
            pojo.setServiceToken(attendee.getServiceToken());
            pojo.setMobilePlatform(attendee.getMobilePlatform());
            pojo.setExhibition(attendee.getExhibition());
            pojo.setLocation(attendee.getLocation());
            pojo.setDistance(attendee.getDistance());
            pojo.setCheckInAt(attendee.getCheckInAt());
            pojo.getCheckInHistories().addAll(attendee.getCheckInHistories());
            list.add(pojo);
        }
        return list;
    }
}
