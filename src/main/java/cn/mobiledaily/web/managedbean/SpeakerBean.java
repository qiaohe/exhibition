package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class SpeakerBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Speaker newSpeaker;

    @PostConstruct
    public void init() {
        newSpeaker = createSpeaker();
    }

    public void persist() {
        try {
            newSpeaker.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
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

    public List<Speaker> getSpeakers() {
        return exhibitionService.findSpeakerByCode(userBean.getExhibitionCode());
    }

    private Speaker createSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setCreatedBy(userBean.getUser());
        return speaker;
    }

    public ExhibitionService getExhibitionService() {
        return exhibitionService;
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Speaker getNewSpeaker() {
        return newSpeaker;
    }

    public void setNewSpeaker(Speaker newSpeaker) {
        this.newSpeaker = newSpeaker;
    }
}
