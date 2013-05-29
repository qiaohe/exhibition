package cn.mobiledaily.domain;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable, ExhibitionContent {
    private static final String COLLECTION_SUFFIX = ".news";
    private String id;
    @DBRef
    @CreatedBy
    private User createdBy;
    @CreatedDate
    private Date createdAt;
    @DBRef
    @LastModifiedBy
    private User updatedBy;
    @LastModifiedDate
    private Date updatedAt;
    @Transient
    private Exhibition exhibition;
    private String title;
    private String content;

    @Override
    public String getCollectionSuffix() {
        return COLLECTION_SUFFIX;
    }

    @Override
    public Exhibition getExhibition() {
        return exhibition;
    }

    @Override
    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
