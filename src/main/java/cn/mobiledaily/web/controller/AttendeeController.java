package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.pushnotification.MobilePlatform;
import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.web.assembler.CheckInAssembler;
import cn.mobiledaily.web.common.DownstreamPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/attendees")
@Transactional
public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private CheckInAssembler checkInAssembler;

    @RequestMapping(value = "/exhibition/{exhibitionCode}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<Attendee> getAttendees(@PathVariable String exhibitionCode) {
        return attendeeService.findAttendees(exhibitionCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody AttendeeRegisterRequest arg) {
        attendeeService.register(arg.getServiceToken(), arg.getExhibitionCode(), arg.getMobilePlatform());
        Exhibition exhibition = exhibitionService.findByCode(arg.getExhibitionCode());
        DownstreamPusher.push(exhibition.getName());
    }

    @RequestMapping(value = "/check_in", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void checkIn(String serviceToken, String exhibitionCode,
                        double latitude, double longitude, String address) {
        Attendee attendee = attendeeService.checkIn(serviceToken, exhibitionCode, latitude, longitude, address);
        DownstreamPusher.push(DownstreamPusher.CHECK_IN_CHANNEL, checkInAssembler.toCheckIn(attendee));
    }

    public static final class AttendeeRegisterRequest {
        private String serviceToken;
        private String exhibitionCode;
        private MobilePlatform mobilePlatform;

        public String getServiceToken() {
            return serviceToken;
        }

        public void setServiceToken(String serviceToken) {
            this.serviceToken = serviceToken;
        }

        public String getExhibitionCode() {
            return exhibitionCode;
        }

        public void setExhibitionCode(String exhibitionCode) {
            this.exhibitionCode = exhibitionCode;
        }

        public MobilePlatform getMobilePlatform() {
            return mobilePlatform;
        }

        public void setMobilePlatform(MobilePlatform mobilePlatform) {
            this.mobilePlatform = mobilePlatform;
        }
    }
}
