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
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

    @Override
    public Exhibition findByCode(String code) {
        return exhibitionRepository.findByCode(code);
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
}
