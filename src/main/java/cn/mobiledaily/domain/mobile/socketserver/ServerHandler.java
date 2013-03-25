package cn.mobiledaily.domain.mobile.socketserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.channel.*;
import org.jboss.netty.util.internal.StringUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/18/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServerHandler extends SimpleChannelHandler {

    private boolean isStartupMessage(final Object message) {
        return StringUtils.isNotEmpty(message.toString()) &&
                StringUtils.contains(message.toString(), "macAddress") &&
                StringUtils.contains(message.toString(), "appCode");

    }

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (isStartupMessage(e.getMessage())) {
            ObjectMapper mapper = new ObjectMapper();
            StartupMessage message = mapper.readValue(StringUtil.stripControlCharacters(e.getMessage()), StartupMessage.class);
            ChannelManager.getInstance().bind(ctx.getChannel(), message.getServiceToken());
        }
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelOpen(ctx, e);
        ChannelManager.getInstance().addChannel(ctx.getChannel());
    }

    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getChannel().close();
        ChannelManager.getInstance().unbind(ctx.getChannel());
        ChannelManager.getInstance().removeChannel(ctx.getChannel());
    }
}