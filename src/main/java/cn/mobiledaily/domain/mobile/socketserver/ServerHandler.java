package cn.mobiledaily.domain.mobile.socketserver;

import org.jboss.netty.channel.*;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/18/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServerHandler extends SimpleChannelHandler {

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof StartupMessage) {
            ChannelManager.getInstance().bind(ctx.getChannel(), e.getMessage().toString());
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