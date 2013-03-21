package cn.mobiledaily.domain.mobile.pushnotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/20/13
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractPusher implements Pusher {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Pusher.class);

    public void push(final String message, final String serviceToken) {
        push(message, Collections.singletonList(serviceToken));
    }
}
