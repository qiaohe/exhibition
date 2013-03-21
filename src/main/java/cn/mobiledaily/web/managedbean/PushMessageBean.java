package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.service.PushMessageService;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.ObjectInputStream;

@ManagedBean
public class PushMessageBean {
    private String title;
    private String body;
    private String recipients;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{pushMessageService}")
    transient private PushMessageService pushMessageService;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setPushMessageService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{pushMessageService}", PushMessageService.class));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public PushMessageService getPushMessageService() {
        return pushMessageService;
    }

    public void setPushMessageService(PushMessageService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    public void push() {
        PushMessage message = new PushMessage(title, body, recipients);
        try {
            pushMessageService.pushMessage(getUserBean().getExhibitionCode(), message);
        } catch (Exception e) {
            RequestContext.getCurrentInstance().addCallbackParam("error", e.getMessage());
        }
    }
}
