package cn.mobiledaily.domain.mobile;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public final class Location implements Serializable {
    private static final long serialVersionUID = 4736828905659788836L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private CheckInEntry checkInEntry;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Location() {
    }

    public Location(BigDecimal longitude, BigDecimal latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //<editor-fold desc="fields">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CheckInEntry getCheckInEntry() {
        return checkInEntry;
    }

    public void setCheckInEntry(CheckInEntry checkInEntry) {
        this.checkInEntry = checkInEntry;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    //</editor-fold>

    public static Location valueOf(final BigDecimal longitude, final BigDecimal latitude) {
        return new Location(longitude, latitude);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass())
                && EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
