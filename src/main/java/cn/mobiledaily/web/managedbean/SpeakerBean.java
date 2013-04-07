package cn.mobiledaily.web.managedbean;

import cn.mobiledaily.domain.Speaker;
import cn.mobiledaily.service.FileService;
import cn.mobiledaily.web.common.SpringContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@ManagedBean(name = "speaker")
@ViewScoped
public class SpeakerBean extends ExhibitionContentBean<Speaker> {
    @ManagedProperty("#{fileService}")
    transient private FileService fileService;
    private Collator zhCollator = Collator.getInstance(Locale.CHINESE);
    private Comparator<Speaker> speakerComparator = new Comparator<Speaker>() {
        @Override
        public int compare(Speaker o1, Speaker o2) {
            return zhCollator.compare(o1.getName(), o2.getName());
        }
    };

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        fileService = SpringContext.getFileService();
    }

    @Override
    protected Class<Speaker> getContentType() {
        return Speaker.class;
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

    @Override
    public List<Speaker> getItems() {
        List<Speaker> list = super.getItems();
        Collections.sort(list, speakerComparator);
        return list;
    }
}
