package cn.mobiledaily.domain.mobile.pushnotification;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/20/13
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Pusher {
    public void push(final String message, final String serviceToken);

    public void push(final String message, final List<String> serviceTokens);

}
