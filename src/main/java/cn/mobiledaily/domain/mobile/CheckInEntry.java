package cn.mobiledaily.domain.mobile;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckInEntry {
    private final Location location;
    private final Date date;

    public CheckInEntry(Location location, Date date) {
        this.location = location;
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

}
