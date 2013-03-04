package cn.mobiledaily.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 1:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Exhibitor {
    private String name;
    private String stand;
    private String company;
    private String email;
    private String address;
    private String website;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
