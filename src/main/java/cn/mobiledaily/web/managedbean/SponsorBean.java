package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Sponsor;
import cn.mobiledaily.service.ExhibitionService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
public class SponsorBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Sponsor newSponsor;

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

    public void persist() {
        try {
            exhibitionService.persist(newSponsor);
            newSponsor = createSponsor();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    private Sponsor createSponsor() {
        Sponsor sponsor = new Sponsor();
        sponsor.setCreatedBy(userBean.getUser());
        sponsor.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        return sponsor;
    }
}