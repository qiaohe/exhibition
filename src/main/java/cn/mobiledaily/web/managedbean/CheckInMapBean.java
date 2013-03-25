package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.service.AttendeeService;
import cn.mobiledaily.service.ReportService;
import cn.mobiledaily.web.assembler.CheckInAssembler;
import cn.mobiledaily.web.pojo.CheckIn;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class CheckInMapBean implements Serializable {
    private static final long serialVersionUID = 7698962184901729859L;
    @ManagedProperty("#{reportService}")
    transient private ReportService reportService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{attendeeService}")
    transient private AttendeeService attendeeService;
    @ManagedProperty("#{checkInAssembler}")
    transient private CheckInAssembler checkInAssembler;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setReportService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{reportService}", ReportService.class));
        setAttendeeService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{attendeeService}", AttendeeService.class));
        setCheckInAssembler(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{checkInAssembler}", CheckInAssembler.class));
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

    public void setCheckInAssembler(CheckInAssembler checkInAssembler) {
        this.checkInAssembler = checkInAssembler;
    }

    public List<CheckIn> getCheckIns() {
        return checkInAssembler.toCheckIn(attendeeService.findAttendees(userBean.getExhibitionCode()));
    }
}
