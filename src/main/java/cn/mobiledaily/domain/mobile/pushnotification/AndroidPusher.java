package cn.mobiledaily.domain.mobile.pushnotification;

import cn.mobiledaily.domain.mobile.socketserver.ChannelManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/20/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AndroidPusher extends AbstractPusher implements Pusher {
    @Override
    public void push(String message, List<String> serviceTokens) {
        ChannelManager.getInstance().sendDownStreamMessage(serviceTokens, message);
    }
}
