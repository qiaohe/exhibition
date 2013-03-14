package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.ExhibitionService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class SpeakerBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Speaker newSpeaker;

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

    public void persist() {
        try {
            exhibitionService.persist(newSpeaker);
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

    private Speaker createSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setCreatedBy(userBean.getUser());
        speaker.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        return speaker;
    }
}