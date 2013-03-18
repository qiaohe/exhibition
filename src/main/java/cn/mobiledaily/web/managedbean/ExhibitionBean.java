package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
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
public class ExhibitionBean implements Serializable {
    private static final long serialVersionUID = 8754820461982771280L;
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Exhibition newExhibition;
    private Exhibition editExhibition;

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
            exhibitionService.save(newExhibition);
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

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editExhibition);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editExhibition);
    }

    private Exhibition createExhibition() {
        Exhibition exhibition = new Exhibition();
        exhibition.setCreatedBy(userBean.getUser());
        return exhibition;
    }
}
