package cn.mobiledaily.service;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import cn.mobiledaily.exception.EntityNotFoundException;

import java.util.List;

public interface AttendeeService {
    void register(String serviceToken, String exhibitionCode, MobilePlatform mobilePlatform) throws EntityNotFoundException;

    List<Attendee> findAttendees(String exhibitionCode);

    List<CheckInEntry> findCheckInEntries(String exhibitionCode);

    Attendee checkIn(String serviceToken, String exhibitionCode, double latitude, double longitude, String address);
}
