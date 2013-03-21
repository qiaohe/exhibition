package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.ExhibitionService;
import cn.mobiledaily.service.FileService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.*;
import java.util.List;

@ManagedBean
@ViewScoped
public class SpeakerBean implements Serializable {
    private static final long serialVersionUID = 8287843545306776012L;
    @ManagedProperty("#{exhibitionService}")
    transient private ExhibitionService exhibitionService;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    @ManagedProperty("#{fileService}")
    transient private FileService fileService;
    private Speaker newSpeaker;
    private Speaker editSpeaker;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        setExhibitionService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{exhibitionService}", ExhibitionService.class));
        setFileService(facesContext.getApplication().evaluateExpressionGet(facesContext, "#{fileService}", FileService.class));
    }

    public void setExhibitionService(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<Speaker> getSpeakers() {
        return exhibitionService.findSpeakerByCode(userBean.getExhibitionCode());
    }

    public Speaker getNewSpeaker() {
        if (newSpeaker == null) {
            newSpeaker = createSpeaker();
        }
        return newSpeaker;
    }

    public Speaker getEditSpeaker() {
        return editSpeaker;
    }

    public void updateSpeakerPhoto(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        try (InputStream in = file.getInputstream()) {
            String filename = file.getFileName();
            String newFilename = userBean.getExhibitionCode() + '/' + System.nanoTime() +
                    filename.substring(filename.lastIndexOf('.'));
            fileService.save(in, newFilename);
            editSpeaker.setPhoto(newFilename);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
        }
    }

    public void createSpeakerPhoto(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        try (InputStream in = file.getInputstream()) {
            String filename = file.getFileName();
            String newFilename = userBean.getExhibitionCode() + '/' + System.nanoTime() +
                    filename.substring(filename.lastIndexOf('.'));
            fileService.save(in, newFilename);
            newSpeaker.setPhoto(newFilename);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
        }
    }

    public void persist() {
        try {
            exhibitionService.save(newSpeaker);
            newSpeaker = createSpeaker();
        } catch (Exception e) {
            FacesMessage msg;
            if (newSpeaker.getCreatedBy() == null) {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", "Please change login account");
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage());
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void edit(long id) {
        editSpeaker = exhibitionService.findSpeakerById(id);
    }

    public void update(ActionEvent actionEvent) {
        try {
            exhibitionService.save(editSpeaker);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
            RequestContext.getCurrentInstance().addCallbackParam("error", 1);
        }
    }

    public void remove(ActionEvent actionEvent) {
        exhibitionService.delete(editSpeaker);
    }

    private Speaker createSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setCreatedBy(userBean.getUser());
        speaker.setExhibition(exhibitionService.findByCode(userBean.getExhibitionCode()));
        return speaker;
    }
}
