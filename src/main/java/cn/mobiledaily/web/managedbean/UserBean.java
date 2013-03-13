package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = -2228704796517429583L;
    @ManagedProperty("#{userService}")
    transient private UserService userService;
    private User user;
    private String exhibitionCode;

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        user = userService.findByUsername(username);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExhibitionCode() {
        return exhibitionCode;
    }

    public String home(String exhibitionCode) {
        this.exhibitionCode = exhibitionCode;
        return "speaker.xhtml";
    }
}
