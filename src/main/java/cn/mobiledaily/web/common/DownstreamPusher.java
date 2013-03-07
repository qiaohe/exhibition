package cn.mobiledaily.web.common;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import javax.faces.application.FacesMessage;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/7/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public final class DownstreamPusher {
    private static final String DEFAULT_CHANEL = "/notifications";

    public static void push(final String title, final String message) {
        push(DEFAULT_CHANEL, title, message);
    }

    public static void push(final String channel, final String title, final String message) {
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push(channel, new FacesMessage(title, message));
    }
}
