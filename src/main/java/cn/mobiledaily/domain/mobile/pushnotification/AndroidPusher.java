package cn.mobiledaily.domain.mobile.pushnotification;

import cn.mobiledaily.domain.mobile.socketserver.ChannelManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AndroidPusher extends AbstractPusher implements Pusher {
    @Override
    public void push(String message, List<String> serviceTokens) {
        ChannelManager.getInstance().sendDownStreamMessage(serviceTokens, message);
    }
}
