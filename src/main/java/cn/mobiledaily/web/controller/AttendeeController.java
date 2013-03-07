package cn.mobiledaily.web.controller;

import cn.mobiledaily.domain.mobile.Location;
import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.web.common.DownstreamPusher;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/6/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/attendees")
public class AttendeeController {
    @Autowired
    private AttendeeService attendeeService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public String register(@RequestBody @Valid AttendeeRegisterRequest arg, BindingResult result) {
        if (result.hasErrors()) return result.getAllErrors().toString();
        attendeeService.register(arg.getServiceToken(), arg.getExhibitionId());
        DownstreamPusher.push("CCBN", arg.getServiceToken());
        return null;
    }

    @RequestMapping(value = "/{attendeeId}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String checkIn(@PathVariable long attendeeId, @RequestBody Location location) {
        attendeeService.checkIn(attendeeId, location);
        return null;
    }

    public static final class AttendeeRegisterRequest {
        @NotEmpty
        private String serviceToken;
        @Range
        private Long exhibitionId;

        public String getServiceToken() {
            return serviceToken;
        }

        public void setServiceToken(String serviceToken) {
            this.serviceToken = serviceToken;
        }

        public Long getExhibitionId() {
            return exhibitionId;
        }

        public void setExhibitionId(Long exhibitionId) {
            this.exhibitionId = exhibitionId;
        }
    }
}
