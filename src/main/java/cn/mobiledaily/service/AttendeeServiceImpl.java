package cn.mobiledaily.service;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.CheckInEntry;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import cn.mobiledaily.exception.EntityNotFoundException;
import cn.mobiledaily.repository.AttendeeRepository;
import cn.mobiledaily.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static cn.mobiledaily.exception.EntityNotFoundException.EXHIBITION_ERROR_FORMAT;

@Service(value = "attendeeService")
@Transactional(readOnly = true)
public class AttendeeServiceImpl implements AttendeeService {
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Override
    @Transactional
    public void register(String serviceToken, String exhibitionCode, MobilePlatform mobilePlatform) throws EntityNotFoundException {
        Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
        if (exhibition == null)
            throw new EntityNotFoundException(String.format(EXHIBITION_ERROR_FORMAT, exhibitionCode));
        Attendee attendee = new Attendee(serviceToken, exhibition, mobilePlatform);
        attendeeRepository.save(attendee);
    }

    @Override
    @Transactional
    public void checkIn(String serviceToken, String exhibitionCode, double latitude, double longitude, String address) {
        List<Attendee> attendees = attendeeRepository.findByServiceTokenAndExhibition_Code(serviceToken, exhibitionCode);
        Attendee attendee;
        if (attendees.isEmpty()) {
            Exhibition exhibition = exhibitionRepository.findByCode(exhibitionCode);
            attendee = new Attendee(serviceToken, exhibition);
        } else {
            attendee = attendees.get(0);
        }
        Location location = new Location(longitude, latitude, address);
        attendee.setLocation(location);
        attendee.addCheckInHistory(new CheckInEntry(location));
        attendee.setCheckInAt(new Date());
        attendeeRepository.save(attendee);
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
