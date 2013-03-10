package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.exception.EntityNotFoundException;
import cn.mobiledaily.repository.AttendeeRepository;
import cn.mobiledaily.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cn.mobiledaily.exception.EntityNotFoundException.ATTENDEE_ERROR_FORMAT;
import static cn.mobiledaily.exception.EntityNotFoundException.EXHIBITION_ERROR_FORMAT;

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
    public void register(String serviceToken, String exhibitionCode) throws EntityNotFoundException {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        if (exhibition == null)
            throw new EntityNotFoundException(String.format(EXHIBITION_ERROR_FORMAT, exhibitionCode));
        Attendee attendee = new Attendee(serviceToken, exhibition);
        attendeeRepository.persist(attendee);
    }

    @Override
    public void checkIn(Long attendeeId, Location location) throws EntityNotFoundException {
        Attendee attendee = attendeeRepository.findById(attendeeId);
        if (attendee == null)
            throw new EntityNotFoundException(String.format(ATTENDEE_ERROR_FORMAT, attendeeId));
        attendee.addCheckInHistory(new CheckInEntry(location));
        attendeeRepository.persist(attendee);
    }

    @Override
    public List<Attendee> findAttendees(String exhibitionCode) {
        return attendeeRepository.findAll();
    }

    @Override
    public List<CheckInEntry> findCheckInEntries(String exhibitionCode) {
        return null;
    }
}
