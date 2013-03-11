package cn.mobiledaily.web.assembler;

import cn.mobiledaily.domain.*;
import cn.mobiledaily.web.pojo.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExhibitionAssembler {
    public ExhibitionPOJO toPOJO(Exhibition exhibition) {
        ExhibitionPOJO exhibitionPOJO = new ExhibitionPOJO();
        exhibitionPOJO.setUpdatedAt(exhibition.getUpdatedAt());
        exhibitionPOJO.setName(exhibition.getName());
        exhibitionPOJO.setDescription(exhibition.getDescription());
        exhibitionPOJO.setAddress(exhibition.getAddress());
        exhibitionPOJO.setStartDate(exhibition.getStartDate());
        exhibitionPOJO.setEndDate(exhibition.getEndDate());
        exhibitionPOJO.setOrganizer(exhibition.getOrganizer());
        return exhibitionPOJO;
    }

    public void combineEventSchedules(ExhibitionPOJO exhibitionPOJO, List<EventSchedule> eventSchedules) {
        List<EventSchedulePOJO> list = exhibitionPOJO.getEventSchedules();
        list.clear();
        for (EventSchedule eventSchedule : eventSchedules) {
            EventSchedulePOJO eventSchedulePOJO = new EventSchedulePOJO();
            eventSchedulePOJO.setName(eventSchedule.getName());
            eventSchedulePOJO.setDescription(eventSchedule.getDescription());
            eventSchedulePOJO.setPlace(eventSchedule.getPlace());
            eventSchedulePOJO.setDate(eventSchedule.getDate());
            list.add(eventSchedulePOJO);
        }
    }

    public void combineExhibitors(ExhibitionPOJO exhibitionPOJO, List<Exhibitor> exhibitors) {
        List<ExhibitorPOJO> list = exhibitionPOJO.getExhibitors();
        list.clear();
        for (Exhibitor exhibitor : exhibitors) {
            ExhibitorPOJO exhibitorPOJO = new ExhibitorPOJO();
            exhibitorPOJO.setName(exhibitor.getName());
            exhibitorPOJO.setStand(exhibitor.getStand());
            exhibitorPOJO.setCompany(exhibitor.getCompany());
            exhibitorPOJO.setEmail(exhibitor.getEmail());
            exhibitorPOJO.setAddress(exhibitor.getAddress());
            exhibitorPOJO.setWebsite(exhibitor.getWebsite());
            exhibitorPOJO.setCategory(exhibitor.getCategory());
            list.add(exhibitorPOJO);
        }
    }

    public void combineSpeakers(ExhibitionPOJO exhibitionPOJO, List<Speaker> speakers) {
        List<SpeakerPOJO> list = exhibitionPOJO.getSpeakers();
        list.clear();
        for (Speaker speaker : speakers) {
            SpeakerPOJO speakerPOJO = new SpeakerPOJO();
            speakerPOJO.setName(speaker.getName());
            speakerPOJO.setProfile(speaker.getProfile());
            speakerPOJO.setEmail(speaker.getEmail());
            speakerPOJO.setPosition(speaker.getPosition());
            speakerPOJO.setCompany(speaker.getCompany());
            speakerPOJO.setMobilePhone(speaker.getMobilePhone());
            list.add(speakerPOJO);
        }
    }

    public void combineSponsors(ExhibitionPOJO exhibitionPOJO, List<Sponsor> sponsors) {
        List<SponsorPOJO> list = exhibitionPOJO.getSponsors();
        list.clear();
        for (Sponsor sponsor : sponsors) {
            SponsorPOJO sponsorPOJO = new SponsorPOJO();
            sponsorPOJO.setName(sponsor.getName());
            sponsorPOJO.setBannerImage(sponsor.getBannerImage());
            sponsorPOJO.setWebsite(sponsor.getWebsite());
            list.add(sponsorPOJO);
        }
    }
}
