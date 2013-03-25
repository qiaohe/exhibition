package cn.mobiledaily.service;

import cn.mobiledaily.domain.*;
import cn.mobiledaily.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("exhibitionService")
@Transactional(readOnly = true)
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
    public void save(Exhibition exhibition) {
        exhibition.setUpdatedAt(new Date());
        exhibitionRepository.save(exhibition);
    }

    @Override
    @Transactional
    public void save(Exhibitor exhibitor) {
        exhibitorRepository.save(exhibitor);
        exhibitor.getExhibition().setUpdatedAt(new Date());
        exhibitionRepository.save(exhibitor.getExhibition());
    }

    @Override
    @Transactional
    public void save(EventSchedule eventSchedule) {
        eventScheduleRepository.save(eventSchedule);
        eventSchedule.getExhibition().setUpdatedAt(new Date());
        exhibitionRepository.save(eventSchedule.getExhibition());
    }

    @Override
    @Transactional
    public void save(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
        sponsor.getExhibition().setUpdatedAt(new Date());
        exhibitionRepository.save(sponsor.getExhibition());
    }

    @Override
    @Transactional
    public void save(Speaker speaker) {
        speakerRepository.save(speaker);
        speaker.getExhibition().setUpdatedAt(new Date());
        exhibitionRepository.save(speaker.getExhibition());
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
        return exhibitionRepository.findOne(id);
    }

    @Override
    public EventSchedule findEventScheduleById(long id) {
        return eventScheduleRepository.findOne(id);
    }

    @Override
    public Exhibitor findExhibitorById(long id) {
        return exhibitorRepository.findOne(id);
    }

    @Override
    public Speaker findSpeakerById(long id) {
        return speakerRepository.findOne(id);
    }

    @Override
    public Sponsor findSponsorById(long id) {
        return sponsorRepository.findOne(id);
    }

    @Override
    public List<EventSchedule> findEventScheduleByCode(String code) {
        return eventScheduleRepository.findByExhibitionCode(code);
    }

    @Override
    public List<Exhibitor> findExhibitorByCode(String code) {
        return exhibitorRepository.findByExhibitionCode(code);
    }

    @Override
    public List<Speaker> findSpeakerByCode(String code) {
        return speakerRepository.findByExhibitionCode(code);
    }

    @Override
    public List<Sponsor> findSponsorByCode(String code) {
        return sponsorRepository.findByExhibitionCode(code);
    }

    @Override
    @Transactional
    public void delete(Exhibition exhibition) {
        exhibitionRepository.delete(exhibition);
    }

    @Override
    @Transactional
    public void delete(Exhibitor exhibitor) {
        exhibitorRepository.delete(exhibitor);
    }

    @Override
    @Transactional
    public void delete(EventSchedule eventSchedule) {
        eventScheduleRepository.delete(eventSchedule);
    }

    @Override
    @Transactional
    public void delete(Sponsor sponsor) {
        sponsorRepository.delete(sponsor);
    }

    @Override
    @Transactional
    public void delete(Speaker speaker) {
        speakerRepository.delete(speaker);
    }
}
