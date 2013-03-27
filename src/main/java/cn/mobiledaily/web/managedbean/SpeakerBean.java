package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.FileService;
import cn.mobiledaily.web.common.SpringContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

@ManagedBean(name = "speaker")
@ViewScoped
public class SpeakerBean extends ExhibitionContentBean<Speaker> {
    @ManagedProperty("#{fileService}")
    transient private FileService fileService;

    @PostConstruct
    private void init() {
        setContentType(Speaker.class);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        fileService = SpringContext.getFileService();
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void updateSpeakerPhoto(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        try (InputStream in = file.getInputstream()) {
            String filename = file.getFileName();
            String newFilename = getUserBean().getExhibitionCode() + '/' + System.nanoTime() +
                    filename.substring(filename.lastIndexOf('.'));
            fileService.save(in, newFilename);
            getItem().setPhoto(newFilename);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Internal Error", e.getMessage()));
        }
    }
}
