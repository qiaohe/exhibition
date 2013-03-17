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

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Channel ch = e.getChannel();
        ch.close();
    }
}


