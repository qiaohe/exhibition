package cn.mobiledaily.service;

import cn.mobiledaily.domain.*;
import cn.mobiledaily.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("exhibitionService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ExhibitionRepository exhibitionRepository;
    @Autowired
    private EventScheduleRepository eventScheduleRepository;
    @Autowired
    private ExhibitorRepository exhibitorRepository;
    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    @Transactional
    public void persist(Exhibition exhibition) {
        exhibitionRepository.persist(exhibition);
    }

    @Override
    @Transactional
    public void persist(Exhibitor exhibitor) {
        exhibitorRepository.persist(exhibitor);
    }

    @Override
    @Transactional
    public void persist(EventSchedule eventSchedule) {
        eventScheduleRepository.persist(eventSchedule);
    }

    @Override
    @Transactional
    public void persist(Sponsor sponsor) {
        sponsorRepository.persist(sponsor);
    }

    @Override
    @Transactional
    public void persist(Speaker speaker) {
        speakerRepository.persist(speaker);
    }

    @Override
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

    @Override
    public Exhibition findByCode(String code) {
        return exhibitionRepository.findByCode(code);
    }

    @Override
    public Exhibition findById(Long id) {
        return exhibitionRepository.findById(id);
    }

    @Override
    public EventSchedule findEventScheduleById(long id) {
        return eventScheduleRepository.findById(id);
    }

    @Override
    public Exhibitor findExhibitorById(long id) {
        return exhibitorRepository.findById(id);
    }

    @Override
    public Speaker findSpeakerById(long id) {
        return speakerRepository.findById(id);
    }

    @Override
    public Sponsor findSponsorById(long id) {
        return sponsorRepository.findById(id);
    }

    @Override
    public List<EventSchedule> findEventScheduleByCode(String code) {
        return eventScheduleRepository.findByCode(code);
    }

    @Override
    public List<Exhibitor> findExhibitorByCode(String code) {
        return exhibitorRepository.findByCode(code);
    }

    @Override
    public List<Speaker> findSpeakerByCode(String code) {
        return speakerRepository.findByCode(code);
    }

    @Override
    public List<Sponsor> findSponsorByCode(String code) {
        return sponsorRepository.findByCode(code);
    }

    @Override
    @Transactional
    public void remove(Exhibition exhibition) {
        exhibitionRepository.remove(exhibition);
    }

    @Override
    @Transactional
    public void remove(Exhibitor exhibitor) {
        exhibitorRepository.remove(exhibitor);
    }

    @Override
    @Transactional
    public void remove(EventSchedule eventSchedule) {
        eventScheduleRepository.remove(eventSchedule);
    }

    @Override
    @Transactional
    public void remove(Sponsor sponsor) {
        sponsorRepository.remove(sponsor);
    }

    @Override
    @Transactional
    public void remove(Speaker speaker) {
        speakerRepository.remove(speaker);
    }
}
