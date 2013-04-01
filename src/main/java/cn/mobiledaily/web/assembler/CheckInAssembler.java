package cn.mobiledaily.web.assembler;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.web.pojo.CheckIn;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CheckInAssembler {
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
}
