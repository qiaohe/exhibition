package cn.mobiledaily.domain;

import cn.mobiledaily.domain.mobile.ExhibitionContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PushMessage implements ExhibitionContent {
    public static final String COLLECTION_SUFFIX = ".push_message";
    public static final char RECIPIENT_SEPARATOR = ',';
    private String id;
    @DBRef
    private Exhibition exhibition;
    private String title;
    private String body;
    private String recipients;
    private Date deliverDate = new Date();
    public PushMessage() {}

    public PushMessage(String title, String body, String recipients) {
        this();
        this.title = title;
        this.body = body;
        this.recipients = recipients;
    }

    @Override
    public String getCollectionSuffix() {
        return COLLECTION_SUFFIX;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
