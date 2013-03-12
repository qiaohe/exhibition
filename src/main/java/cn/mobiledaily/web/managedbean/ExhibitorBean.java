package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.Exhibitor;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/12/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
public class ExhibitorBean {
    @ManagedProperty("#{exhibitionService}")
    private ExhibitionService exhibitionService;
    @ManagedProperty("#{userService}")
    private UserService userService;
    private Exhibitor exhibitor = new Exhibitor();
    private String code;

    public List<Exhibitor> getExhibitors() {
        return exhibitionService.findExhibitorByCode(code);
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setExhibitor(Exhibitor exhibitor) {
        this.exhibitor = exhibitor;
    }

    public Exhibitor getExhibitor() {
        return exhibitor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void persist() {
        Exhibition exhibition = exhibitionService.findByCode(getCode());
        exhibitor.setExhibition(exhibition);
        exhibitionService.persist(exhibitor);
    }
}
