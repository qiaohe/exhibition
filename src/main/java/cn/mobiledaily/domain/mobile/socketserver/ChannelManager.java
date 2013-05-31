package cn.mobiledaily.domain.mobile.socketserver;

import com.exhibition.domain.mobile.MessageObjects;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelLocal;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public final class ChannelManager {
    private static final ChannelGroup CHANNELS = new DefaultChannelGroup(Server.class.getSimpleName());
    private static final ChannelLocal<String> TOKEN_HOLDER = new ChannelLocal<>(true);
    private static final ChannelManager INSTANCE = new ChannelManager();

    private ChannelManager() {
    }

    public static ChannelManager getInstance() {
        return INSTANCE;
    }

    public List<String> getTokens() {
        List<String> result = new ArrayList<>();
        for (Channel channel : CHANNELS) {
            final String token = TOKEN_HOLDER.get(channel);
            result.add(token);
        }
        return result;
    }

    public Channel getChannel(final String token) {
        for (Channel channel : CHANNELS) {
            if (token.equalsIgnoreCase(TOKEN_HOLDER.get(channel)))
                return channel;
        }
        return null;
    }

    public void addChannel(Channel channel) {
        CHANNELS.add(channel);
    }

    public void removeChannel(Channel channel) {
        CHANNELS.remove(channel);
    }

    public void bind(Channel channel, String serviceToken) {
        TOKEN_HOLDER.set(channel, serviceToken);
    }

    public void unbind(Channel channel) {
        TOKEN_HOLDER.remove(channel);
    }

    public void sendDownStreamMessage(final String serviceToken, final String message) {
        Channel ch = getChannel(serviceToken);
        if (ch != null) {
            ch.write(MessageObjects.stringMessage(message));
        }
    }

    public void sendDownStreamMessage(List<String> serviceTokens, String message) {
        for (String token : serviceTokens) {
            sendDownStreamMessage(token, message);
        }
    }
}
