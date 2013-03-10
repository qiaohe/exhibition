package cn.mobiledaily.service;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AttendeeService {
    void register(String serviceToken, String exhibitionCode) throws EntityNotFoundException;

    void checkIn(Long attendeeId, Location location) throws EntityNotFoundException;

    List<Attendee> findAttendees(String exhibitionCode);

    List<CheckInEntry> findCheckInEntries(String exhibitionCode);
}
