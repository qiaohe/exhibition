package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.News;
import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.service.PushMessageService;
import org.springframework.data.domain.Sort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.ObjectInputStream;

@ManagedBean(name = "news")
@ViewScoped
public class NewsBean extends ExhibitionContentBean<News> {
    private Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
    @ManagedProperty("#{pushMessageService}")
    transient private PushMessageService pushMessageService;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setPushMessageService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{pushMessageService}", PushMessageService.class));
    }

    public void setPushMessageService(PushMessageService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    @Override
    protected Class<News> getContentType() {
        return News.class;
    }

    @Override
    protected Sort getSort() {
        return sort;
    }

    @Override
    public void save() {
        boolean isEdit = isEdit();
        final News news = getItem();
        super.save();
        final String code = getUserBean().getExhibitionCode();
        if (!isEdit) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PushMessage message = new PushMessage("CCBN新闻", news.getTitle(), "");
                    pushMessageService.pushMessage(code, message);
                }
            }).start();
        }
    }
}
