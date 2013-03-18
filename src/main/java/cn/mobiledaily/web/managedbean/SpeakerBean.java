package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
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
public class SpeakerBean implements Serializable {
    private static final long serialVersionUID = -5896769720195338897L;
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Speaker newSpeaker;
    private Speaker editSpeaker;

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

    public List<Speaker> getSpeakers() {
        return exhibitionService.findSpeakerByCode(userBean.getExhibitionCode());
    }

    public Speaker getNewSpeaker() {
        if (newSpeaker == null) {
            newSpeaker = createSpeaker();
        }
        return newSpeaker;
    }

    public Speaker getEditSpeaker() {
        return editSpeaker;
    }

    public void persist() {
        try {
            exhibitionService.save(newSpeaker);
            newSpeaker = createSpeaker();
        } catch (Exception e) {
            FacesMessage msg;
            if (newSpeaker.getCreatedBy() == null) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", "Please change login account");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage());
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void edit(long id) {
        editSpeaker = exhibitionService.findSpeakerById(id);
    }

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editSpeaker);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editSpeaker);
    }

    private Speaker createSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setCreatedBy(userBean.getUser());
        speaker.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        return speaker;
    }
}
