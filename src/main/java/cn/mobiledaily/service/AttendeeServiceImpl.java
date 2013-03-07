package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.repository.AttendeeRepository;
import cn.mobiledaily.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "attendeeService")
@Transactional
public class AttendeeServiceImpl implements AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Override
    public void register(String serviceToken, Long exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId);
        if (exhibition == null) throw new IllegalArgumentException("exhibition id can not be null.");
        Attendee attendee = new Attendee(serviceToken, exhibition);
        attendeeRepository.persist(attendee);
    }

    @Override
    public void checkIn(Long attendeeId, Location location) {
        Attendee attendee = attendeeRepository.findById(attendeeId);
        attendee.addCheckInHistory(new CheckInEntry(location));
        attendeeRepository.persist(attendee);
    }

    @Override
    public List<Attendee> findAttendees(Long exhibitionId) {
        return attendeeRepository.findAll();
    }

    @Override
    public List<CheckInEntry> findCheckInEntries(Long exhibitionId) {
        return null;
    }
}
