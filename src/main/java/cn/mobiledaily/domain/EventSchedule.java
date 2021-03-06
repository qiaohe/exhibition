package cn.mobiledaily.domain;

import cn.mobiledaily.domain.identity.User;
import cn.mobiledaily.domain.mobile.ExhibitionContent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Date;

public class EventSchedule implements Serializable, ExhibitionContent {
    private static final String COLLECTION_SUFFIX = ".event_schedule";
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
    private String name;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private String place;

    public EventSchedule() {
    }

    public EventSchedule(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    @Override
    public String getCollectionSuffix() {
        return COLLECTION_SUFFIX;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    //<editor-fold desc="fields">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date date) {
        this.dateFrom = date;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    //</editor-fold>
}
