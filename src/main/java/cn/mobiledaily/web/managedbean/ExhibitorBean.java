package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibitor;
import cn.mobiledaily.service.ExhibitionService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class ExhibitorBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Exhibitor newExhibitor;

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<Exhibitor> getExhibitors() {
        return exhibitionService.findExhibitorByCode(userBean.getExhibitionCode());
    }

    public Exhibitor getNewExhibitor() {
        if (newExhibitor == null) {
            newExhibitor = createExhibitor();
        }
        return newExhibitor;
    }

    public void persist() {
        try {
            exhibitionService.persist(newExhibitor);
            newExhibitor = createExhibitor();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    private Exhibitor createExhibitor() {
        Exhibitor exhibitor = new Exhibitor();
        exhibitor.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        exhibitor.setCreatedBy(userBean.getUser());
        return exhibitor;
    }
}
