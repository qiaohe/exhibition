package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Exhibition;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.web.common.SpringContext;

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

@ManagedBean(name = "exhibition")
@ViewScoped
public class ExhibitionBean implements Serializable {
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    private Exhibition item;
    private boolean edit;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        exhibitionService = SpringContext.getExhibitionService();
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public List<Exhibition> getItems() {
        return exhibitionService.findAll();
    }

    public Exhibition getItem() {
        return item;
    }

    public void save(ActionEvent actionEvent) {
        try {
            exhibitionService.save(item);
            item = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
        }
    }

    public void edit(String id) {
        item = exhibitionService.findById(id);
        edit = true;
    }

    public void create() {
        if (edit || item == null) {
            item = createItem();
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(item);
    }

    private Exhibition createItem() {
        Exhibition exhibition = new Exhibition();
        edit = false;
        return exhibition;
    }
}
