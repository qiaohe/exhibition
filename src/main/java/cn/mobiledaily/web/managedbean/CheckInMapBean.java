package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.mobile.Attendee;
import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.service.ReportService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@ManagedBean
public class CheckInMapBean {
    @ManagedProperty("#{reportService}")
    transient private ReportService reportService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{attendeeService}")
    transient private AttendeeService attendeeService;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setReportService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{reportService}", ReportService.class));
        setAttendeeService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{attendeeService}", AttendeeService.class));
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setAttendeeService(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    public List<Attendee> getAttendees() {
        return attendeeService.findAttendees(userBean.getExhibitionCode());
    }
}
