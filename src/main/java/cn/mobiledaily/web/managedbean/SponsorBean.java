package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.Sponsor;
import cn.mobiledaily.service.ExhibitionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/12/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
public class SponsorBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    private Sponsor sponsor = new Sponsor();
    private String code;

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public List<Sponsor> getSponsors() {
        return exhibitionService.findSponsorByCode(code);
    }

    public ExhibitionService getExhibitionService() {
        return exhibitionService;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void persist() {
        Exhibition exhibition = exhibitionService.findByCode(getCode());
        sponsor.setExhibition(exhibition);
        exhibitionService.persist(sponsor);
    }
}
