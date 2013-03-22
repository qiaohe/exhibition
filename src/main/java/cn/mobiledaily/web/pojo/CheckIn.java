package cn.mobiledaily.web.pojo;

public class CheckIn {
    private String key;
    private double lat;
    private double lng;
    private String address;
    private String time;

    public CheckIn(String key, double lat, double lng, String address, String time) {
        this.key = key;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.time = time;
    }

    //<editor-fold desc="fields">
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    //</editor-fold>
}
