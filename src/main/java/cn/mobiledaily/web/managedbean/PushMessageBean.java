package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.service.PushMessageService;
import org.primefaces.context.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
public class PushMessageBean {
    private String title;
    private String body;
    private String recipients;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{pushMessageService}")
    private PushMessageService pushMessageService;

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
