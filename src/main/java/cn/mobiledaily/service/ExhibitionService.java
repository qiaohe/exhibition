package cn.mobiledaily.service;

import cn.mobiledaily.domain.*;

import java.util.List;

public interface ExhibitionService {
    void save(Exhibition exhibition);

    void save(Exhibitor exhibitor);

    void save(EventSchedule eventSchedule);

    void save(Sponsor sponsor);

    void save(Speaker speaker);

    List<Exhibition> findAll();

    Exhibition findByCode(String code);

    Exhibition findById(Long id);

    EventSchedule findEventScheduleById(long id);

    Exhibitor findExhibitorById(long id);

    Speaker findSpeakerById(long id);

    Sponsor findSponsorById(long id);

    List<EventSchedule> findEventScheduleByCode(String code);

    List<Exhibitor> findExhibitorByCode(String code);

    List<Speaker> findSpeakerByCode(String code);

    List<Sponsor> findSponsorByCode(String code);

    void delete(Exhibition exhibition);

    void delete(Exhibitor exhibitor);

    void delete(EventSchedule eventSchedule);

    void delete(Sponsor sponsor);

    void delete(Speaker speaker);
}
