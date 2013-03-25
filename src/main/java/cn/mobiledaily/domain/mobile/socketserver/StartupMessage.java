package cn.mobiledaily.domain.mobile.socketserver;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/20/13
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public final class StartupMessage {
    private static final String TOKEN_PATTERN = "%s:%s";
    private String macAddress;
    private String appCode;

    public StartupMessage() {
    }

    public StartupMessage(String macAddress, String appCode) {
        this.macAddress = macAddress;
        this.appCode = appCode;
    }

    public StartupMessage(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @JsonIgnore
    public String getServiceToken() {
        String t = String.format(TOKEN_PATTERN, macAddress, appCode);
        return DigestUtils.sha256Hex(t);
    }
}
