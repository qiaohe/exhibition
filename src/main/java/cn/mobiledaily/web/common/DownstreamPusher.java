package cn.mobiledaily.web.common;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import javax.faces.application.FacesMessage;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/7/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
public final class DownstreamPusher {
    public static final String DEFAULT_CHANNEL = "/notifications";
    public static final String CHECK_IN_CHANNEL = "/check-in";
    private static final String MESSAGE_TEMPLATE_REGISTRATION = "新手机用户登陆成功<br>{0,date,yyyy年MM月dd HH:mm:ss}";

    public static void push(final String title, final String message) {
        push(DEFAULT_CHANNEL, title, message);
    }

    public static <T> void push(final String channel, final T message) {
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push(channel, message);
    }

    public static void push(final String channel, final String title, final String message) {
        push(channel, new FacesMessage(title, message));
    }

    public static void push(final String title) {
        push(title, MessageFormat.format(MESSAGE_TEMPLATE_REGISTRATION, new Date()));
    }
}
