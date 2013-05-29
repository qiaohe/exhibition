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

    public ExhibitorPOJO toPOJO(Exhibitor exhibitor) {
        ExhibitorPOJO pojo = new ExhibitorPOJO();
        pojo.setName(exhibitor.getName());
        pojo.setStand(exhibitor.getStand());
        pojo.setCompany(exhibitor.getCompany());
        pojo.setEmail(exhibitor.getEmail());
        pojo.setAddress(exhibitor.getAddress());
        pojo.setWebsite(exhibitor.getWebsite());
        pojo.setCategory(exhibitor.getCategory());
        pojo.setLocation(exhibitor.getLocation());
        pojo.setPhone(exhibitor.getPhone());
        pojo.setDescription(exhibitor.getDescription());
        return pojo;
    }

    public SpeakerPOJO toPOJO(Speaker speaker) {
        SpeakerPOJO pojo = new SpeakerPOJO();
        pojo.setName(speaker.getName());
        pojo.setProfile(speaker.getProfile());
        pojo.setEmail(speaker.getEmail());
        pojo.setPosition(speaker.getPosition());
        pojo.setCompany(speaker.getCompany());
        pojo.setMobilePhone(speaker.getMobilePhone());
        pojo.setPhoto(speaker.getPhoto());
        return pojo;
    }

    public NewsPOJO toPOJO(News news) {
        NewsPOJO pojo = new NewsPOJO();
        pojo.setTitle(news.getTitle());
        pojo.setContent(news.getContent());
        pojo.setDate(news.getCreatedAt());
        return pojo;
    }

    public void combineEventSchedules(ExhibitionPOJO exhibitionPOJO, List<EventSchedule> eventSchedules) {
        List<EventSchedulePOJO> list = exhibitionPOJO.getEventSchedules();
        list.clear();
        for (EventSchedule eventSchedule : eventSchedules) {
            EventSchedulePOJO eventSchedulePOJO = new EventSchedulePOJO();
            eventSchedulePOJO.setName(eventSchedule.getName());
            eventSchedulePOJO.setDescription(eventSchedule.getDescription());
            eventSchedulePOJO.setPlace(eventSchedule.getPlace());
            eventSchedulePOJO.setDateFrom(eventSchedule.getDateFrom());
            eventSchedulePOJO.setDateTo(eventSchedule.getDateTo());
            list.add(eventSchedulePOJO);
        }
    }

    public void combineExhibitors(ExhibitionPOJO exhibitionPOJO, List<Exhibitor> exhibitors) {
        List<ExhibitorPOJO> list = exhibitionPOJO.getExhibitors();
        list.clear();
        for (Exhibitor exhibitor : exhibitors) {
            list.add(toPOJO(exhibitor));
        }
    }

    public void combineSpeakers(ExhibitionPOJO exhibitionPOJO, List<Speaker> speakers) {
        List<SpeakerPOJO> list = exhibitionPOJO.getSpeakers();
        list.clear();
        for (Speaker speaker : speakers) {
            list.add(toPOJO(speaker));
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
