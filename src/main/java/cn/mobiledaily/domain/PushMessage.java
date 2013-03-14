package cn.mobiledaily.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/14/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PushMessage implements Serializable {
    private static final long serialVersionUID = 6756504156322794898L;
    public static final char RECIPIENT_SEPARATOR = ',';
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Exhibition exhibition;
    private String title;
    private String body;
    private String recipients;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverDate = new Date();
    @Version
    private int version;
    public PushMessage() {}

    public PushMessage(String title, String body, String recipients) {
        this();
        this.title = title;
        this.body = body;
        this.recipients = recipients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
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

    public List<String> getRecipientsAsList() {
        return Arrays.asList(StringUtils.split(recipients, RECIPIENT_SEPARATOR));
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
