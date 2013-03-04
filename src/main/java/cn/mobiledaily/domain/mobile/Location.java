package cn.mobiledaily.domain.mobile;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public final class Location implements Serializable {
    private static final long serialVersionUID = 4736828905659788836L;
    private final BigDecimal longitude;
    private final BigDecimal latitude;

    public Location(BigDecimal longitude, BigDecimal latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

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
