package cn.mobiledaily.service;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeService {
    void register(String serviceToken, Long exhibitionId);

    void checkIn(Long attendeeId, Location location);

    List<Attendee> findAttendees(Long exhibitionId);

    List<CheckInEntry> findCheckInEntries(Long exhibitionId);
}
