package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 3314337831029516501L;
    @ManagedProperty("#{userService}")
    transient private UserService userService;
    private User user;
    private String exhibitionCode;
    private String exhibitionName;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setUserService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userService}", UserService.class));
    }

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

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void changeExhibitionCode(ActionEvent actionEvent) {
        Map<String, Object> attributes = actionEvent.getComponent().getAttributes();
        exhibitionCode = (String) attributes.get("code");
        exhibitionName = (String) attributes.get("name");
    }
}
