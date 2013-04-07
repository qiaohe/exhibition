package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.*;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.web.assembler.ExhibitionAssembler;
import cn.mobiledaily.web.pojo.ExhibitionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("exhibitions")
public class ExhibitionController {
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private ExhibitionAssembler exhibitionAssembler;

    @ResponseBody
    @RequestMapping("{code}")
    public ExhibitionPOJO getByCode(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) {
        long lastModified;
        try {
            lastModified = Long.valueOf(request.getHeader("If-None-Match"));
        } catch (Exception e) {
            lastModified = 0;
        }
        Exhibition exhibition = exhibitionService.findByCode(code);
        if (lastModified == exhibition.getUpdatedAt().getTime()) {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
            return null;
        }
        ExhibitionPOJO exhibitionPOJO = exhibitionAssembler.toPOJO(exhibition);
        exhibitionAssembler.combineEventSchedules(exhibitionPOJO, exhibitionService.findContents(code, EventSchedule.class));
        exhibitionAssembler.combineExhibitors(exhibitionPOJO, exhibitionService.findContents(code, Exhibitor.class));
        exhibitionAssembler.combineSpeakers(exhibitionPOJO, exhibitionService.findContents(code, Speaker.class));
        exhibitionAssembler.combineSponsors(exhibitionPOJO, exhibitionService.findContents(code, Sponsor.class));
        return exhibitionPOJO;
    }
}
