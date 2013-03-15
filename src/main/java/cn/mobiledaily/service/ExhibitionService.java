package cn.mobiledaily.service;

import cn.mobiledaily.domain.*;

import java.util.List;

public interface ExhibitionService {
    void persist(Exhibition exhibition);

    void persist(Exhibitor exhibitor);

    void persist(EventSchedule eventSchedule);

    void persist(Sponsor sponsor);

    void persist(Speaker speaker);

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

    void remove(Exhibition exhibition);

    void remove(Exhibitor exhibitor);

    void remove(EventSchedule eventSchedule);

    void remove(Sponsor sponsor);

    void remove(Speaker speaker);
}
