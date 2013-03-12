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

    List<EventSchedule> findEventScheduleByCode(String code);

    List<Exhibitor> findExhibitorByCode(String code);

    List<Speaker> findSpeakerByCode(String code);

    List<Sponsor> findSponsorByCode(String code);

}
