package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.EventSchedule;
import cn.mobiledaily.service.ExhibitionService;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class EventScheduleBean implements Serializable {
    private static final long serialVersionUID = -2146319579604881329L;
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private EventSchedule newEventSchedule;
    private EventSchedule editEventSchedule;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setExhibitionService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{exhibitionService}", ExhibitionService.class));
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<EventSchedule> getEventSchedules() {
        return exhibitionService.findEventScheduleByCode(userBean.getExhibitionCode());
    }

    public EventSchedule getNewEventSchedule() {
        if (newEventSchedule == null) {
            newEventSchedule = createEventSchedule();
        }
        return newEventSchedule;
    }

    public EventSchedule getEditEventSchedule() {
        return editEventSchedule;
    }

    public void persist() {
        try {
            exhibitionService.save(newEventSchedule);
            newEventSchedule = createEventSchedule();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void edit(long id) {
        editEventSchedule = exhibitionService.findEventScheduleById(id);
    }

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editEventSchedule);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editEventSchedule);
    }

    private EventSchedule createEventSchedule() {
        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        eventSchedule.setCreatedBy(userBean.getUser());
        return eventSchedule;
    }
}
