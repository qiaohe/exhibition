package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibitor;
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
public class ExhibitorBean implements Serializable {
    private static final long serialVersionUID = 7915242610467913827L;
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Exhibitor newExhibitor;
    private Exhibitor editExhibitor;

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

    public List<Exhibitor> getExhibitors() {
        return exhibitionService.findExhibitorByCode(userBean.getExhibitionCode());
    }

    public Exhibitor getNewExhibitor() {
        if (newExhibitor == null) {
            newExhibitor = createExhibitor();
        }
        return newExhibitor;
    }

    public Exhibitor getEditExhibitor() {
        return editExhibitor;
    }

    public void persist() {
        try {
            exhibitionService.save(newExhibitor);
            newExhibitor = createExhibitor();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void edit(long id) {
        editExhibitor = exhibitionService.findExhibitorById(id);
    }

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editExhibitor);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editExhibitor);
    }

    private Exhibitor createExhibitor() {
        Exhibitor exhibitor = new Exhibitor();
        exhibitor.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        exhibitor.setCreatedBy(userBean.getUser());
        return exhibitor;
    }
}
