package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Sponsor;
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
public class SponsorBean implements Serializable {
    private static final long serialVersionUID = -1969885364302011224L;
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Sponsor newSponsor;
    private Sponsor editSponsor;

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

    public List<Sponsor> getSponsors() {
        return exhibitionService.findSponsorByCode(userBean.getExhibitionCode());
    }

    public Sponsor getNewSponsor() {
        if (newSponsor == null) {
            newSponsor = createSponsor();
        }
        return newSponsor;
    }

    public Sponsor getEditSponsor() {
        return editSponsor;
    }

    public void persist() {
        try {
            exhibitionService.save(newSponsor);
            newSponsor = createSponsor();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void edit(long id) {
        editSponsor = exhibitionService.findSponsorById(id);
    }

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editSponsor);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editSponsor);
    }

    private Sponsor createSponsor() {
        Sponsor sponsor = new Sponsor();
        sponsor.setCreatedBy(userBean.getUser());
        sponsor.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        return sponsor;
    }
}
