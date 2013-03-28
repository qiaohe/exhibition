package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.web.common.SpringContext;
import org.springframework.data.domain.Sort;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Base class for exhibition content beans. {@link #setContentType(Class)} must be called after construct.
 * @param <T> ExhibitionContent
 */
public abstract class ExhibitionContentBean<T extends ExhibitionContent> implements Serializable {
    private Class<T> contentType;
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private T item;
    private boolean edit;
    private Sort sort = new Sort(Sort.Direction.DESC, "createdAt");

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        exhibitionService = SpringContext.getExhibitionService();
    }

    protected ExhibitionService getExhibitionService() {
        return exhibitionService;
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    protected UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    protected Class<T> getContentType() {
        return contentType;
    }

    protected void setContentType(Class<T> contentType) {
        this.contentType = contentType;
    }

    protected boolean isEdit() {
        return edit;
    }

    protected void setEdit(boolean edit) {
        this.edit = edit;
    }

    protected Sort getSort() {
        return sort;
    }

    protected void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<T> getItems() {
        return exhibitionService.findContents(userBean.getExhibitionCode(), contentType, sort);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    protected T createItem() {
        try {
            Exhibition exhibition = exhibitionService.findByCode(userBean.getExhibitionCode());
            T item = contentType.newInstance();
            item.setExhibition(exhibition);
            edit = false;
            return item;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try {
            exhibitionService.save(item);
            item = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
    }

    public void create() {
        if (edit || item == null) {
            item = createItem();
        }
    }

    public void edit(String id) {
        item = exhibitionService.findContentById(userBean.getExhibitionCode(), id, contentType);
        edit = true;
    }

    public void delete(ActionEvent event) {
        exhibitionService.delete(item);
    }
}
