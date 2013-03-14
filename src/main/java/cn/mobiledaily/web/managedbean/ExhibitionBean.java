package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.service.ExhibitionService;

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
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Exhibition newExhibition;
    private Exhibition editExhibition;

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<Exhibition> getExhibitions() {
        return exhibitionService.findAll();
    }

    public Exhibition getNewExhibition() {
        if (newExhibition == null) {
            newExhibition = createExhibition();
        }
        return newExhibition;
    }

    public Exhibition getEditExhibition() {
        return editExhibition;
    }

    public void persist(ActionEvent actionEvent) {
        try {
            exhibitionService.persist(newExhibition);
            newExhibition = createExhibition();
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

    public void edit(long id) {
        editExhibition = exhibitionService.findById(id);
    }

    private Exhibition createExhibition() {
        Exhibition exhibition = new Exhibition();
        exhibition.setCreatedBy(userBean.getUser());
        return exhibition;
    }
}
