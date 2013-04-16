package cn.mobiledaily.web.common;

import org.joda.time.DateTime;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import javax.faces.application.FacesMessage;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

public final class DownstreamPusher {
    public static final String DEFAULT_CHANNEL = "/notifications";
    public static final String CHECK_IN_CHANNEL = "/check-in";
    public static final String SHOW_UP_CHANNEL = "/show-up";
    private static final String MESSAGE_TEMPLATE_REGISTRATION = "手机登陆 {0,date,yyyy年MM月dd HH:mm:ss}";
    private static final String MESSAGE_TEMPLATE_CHECK_IN = "手机签到: %s";
    private static final String MESSAGE_TEMPLATE_SHOW_UP = "%s(%s) 抵达会场 %s";

    public static void push(final String title, final String message) {
        push(DEFAULT_CHANNEL, title, message);
    }

    public static <T> void push(final String channel, final T message) {
        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        pushContext.push(channel, message);
    }

    private static void push(final String channel, final String title, final String message) {
        push(channel, new FacesMessage(title, message));
    }

    public static void push(final String title) {
        push(title, MessageFormat.format(MESSAGE_TEMPLATE_REGISTRATION, new Date()));
    }

    public static void pushCheckInMessage(final String title, final String message) {
        push(CHECK_IN_CHANNEL, title, String.format(MESSAGE_TEMPLATE_CHECK_IN, message));
    }

    public static void pushShowUpMessage(final String name, final String company) {
        push(SHOW_UP_CHANNEL, "抵达通知", String.format(MESSAGE_TEMPLATE_SHOW_UP, name, company,
//                new SimpleDateFormat("M月d日 HH:mm").format(new DateTime().toLocalDate())));
                new DateTime().toString("M月d日 HH:mm", Locale.CHINA)));
    }
}
