package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.EventSchedule;
import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.Exhibitor;
import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.web.assembler.ExhibitionAssembler;
import cn.mobiledaily.web.pojo.EventSchedulePOJO;
import cn.mobiledaily.web.pojo.ExhibitionPOJO;
import cn.mobiledaily.web.pojo.ExhibitorPOJO;
import cn.mobiledaily.web.pojo.SpeakerPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("web")
public class ExhibitionMobileController {
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private ExhibitionAssembler exhibitionAssembler;

    @ResponseBody
    @RequestMapping("{exhibitionCode}/schedules")
    public List<EventSchedulePOJO> getSchedules(@PathVariable String exhibitionCode) {
        Sort sort = new Sort("dateFrom", "dateTo");
        Exhibition exhibition = exhibitionService.findByCode(exhibitionCode);
        ExhibitionPOJO exhibitionPOJO = exhibitionAssembler.toPOJO(exhibition);
        List<EventSchedule> schedules = exhibitionService.findContents(exhibitionCode, EventSchedule.class, sort);
        exhibitionAssembler.combineEventSchedules(exhibitionPOJO, schedules);
        return exhibitionPOJO.getEventSchedules();
    }

    @ResponseBody
    @RequestMapping("{exhibitionCode}/exhibitors")
    public List<Map<String, String>> getExhibitors(@PathVariable String exhibitionCode) {
        Sort sort = new Sort("location");
        List<Exhibitor> exhibitors = exhibitionService.findContents(exhibitionCode, Exhibitor.class, sort);
        List<Map<String, String>> list = new ArrayList<>(exhibitors.size());
        for (Exhibitor exhibitor : exhibitors) {
            Map<String, String> v = new HashMap<>();
            list.add(v);
            v.put("i", exhibitor.getId());
            v.put("c", exhibitor.getCompany());
            v.put("l", exhibitor.getLocation());
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("{exhibitionCode}/exhibitors/{id}")
    public ExhibitorPOJO getExhibitor(@PathVariable String exhibitionCode, @PathVariable String id) {
        Exhibitor exhibitor = exhibitionService.findContentById(exhibitionCode, id, Exhibitor.class);
        ExhibitorPOJO pojo = exhibitionAssembler.toPOJO(exhibitor);
        return pojo;
    }

    @ResponseBody
    @RequestMapping("{exhibitionCode}/speakers")
    public List<Map<String, String>> getSpeakers(@PathVariable String exhibitionCode) {
        List<Speaker> speakers = exhibitionService.findContents(exhibitionCode, Speaker.class);
        List<Map<String, String>> list = new ArrayList<>(speakers.size());
        for (Speaker speaker : speakers) {
            Map<String, String> v = new HashMap<>();
            list.add(v);
            v.put("i", speaker.getId());
            v.put("n", speaker.getName());
            v.put("c", speaker.getCompany());
            v.put("p", speaker.getPhoto());
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("{exhibitionCode}/speakers/{id}")
    public SpeakerPOJO getSpeaker(@PathVariable String exhibitionCode, @PathVariable String id) {
        Speaker speaker = exhibitionService.findContentById(exhibitionCode, id, Speaker.class);
        SpeakerPOJO pojo = exhibitionAssembler.toPOJO(speaker);
        return pojo;
    }
}
