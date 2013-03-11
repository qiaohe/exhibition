package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.List;

@ManagedBean
public class ExhibitionBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userService}")
    private UserService userService;
    private Exhibition newExhibition;

    @PostConstruct
    private void init() {
        newExhibition = newExhibition();
    }

    public List<Exhibition> getExhibitions() {
        return exhibitionService.findAll();
    }

    public void persist(ActionEvent actionEvent) {
        try {
            exhibitionService.persist(newExhibition);
            newExhibition = newExhibition();
        } catch (Exception e) {
            FacesMessage msg;
            if (newExhibition.getCreatedBy() == null) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", "Please change login account");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage());
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Exhibition getNewExhibition() {
        return newExhibition;
    }

    public void setNewExhibition(Exhibition newExhibition) {
        this.newExhibition = newExhibition;
    }

    private User getCurrentUser() {
        String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return userService.findByEmail(email);
    }

    private Exhibition newExhibition() {
        Exhibition exhibition = new Exhibition();
        exhibition.setCreatedBy(getCurrentUser());
        return exhibition;
    }
}
