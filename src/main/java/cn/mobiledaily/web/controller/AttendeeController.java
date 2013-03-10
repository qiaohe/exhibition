package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.web.common.DownstreamPusher;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/attendees")
@Transactional
public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;

    @RequestMapping(value = "/exhibition/{exhibitionCode}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<Attendee> getAttendees(@PathVariable String exhibitionCode) {
        return attendeeService.findAttendees(exhibitionCode);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody @Valid AttendeeRegisterRequest arg) {
        attendeeService.register(arg.getServiceToken(), arg.getExhibitionCode());
        DownstreamPusher.push(arg.getExhibitionCode(), arg.getServiceToken());
    }

    @RequestMapping(value = "/{attendeeId}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void checkIn(@PathVariable long attendeeId, @RequestBody Location location) {
        attendeeService.checkIn(attendeeId, location);
    }

    public static final class AttendeeRegisterRequest {
        @NotEmpty
        private String serviceToken;
        @Range
        private String exhibitionCode;

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
    }
}
