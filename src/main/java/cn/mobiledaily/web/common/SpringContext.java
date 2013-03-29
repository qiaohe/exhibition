package cn.mobiledaily.web.common;

import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.FileService;
import cn.mobiledaily.web.assembler.CheckInAssembler;

import javax.faces.context.FacesContext;

/**
 * Get Spring context bean via FacesContext
 */
public class SpringContext {
    private static <T> T getBean(String name, Class<? extends T> type) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{" + name + "}", type);
    }

    public static ExhibitionService getExhibitionService() {
        return getBean("exhibitionService", ExhibitionService.class);
    }

    public static FileService getFileService() {
        return getBean("fileService", FileService.class);
    }

    public static AttendeeService getAttendeeService() {
        return getBean("attendeeService", AttendeeService.class);
    }

    public static CheckInAssembler getCheckInAssembler() {
        return getBean("checkInAssembler", CheckInAssembler.class);
    }
}
