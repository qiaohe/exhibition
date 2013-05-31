package cn.mobiledaily.domain.mobile.socketserver;

import com.exhibition.domain.mobile.MessageObject;
import com.exhibition.domain.mobile.MessageObjects;
import com.exhibition.domain.mobile.ReqToken;
import org.jboss.netty.channel.*;

public class ServerHandler extends SimpleChannelHandler {
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object obj = e.getMessage();
        if (obj != null && obj instanceof MessageObject) {
            MessageObject resp = null;
            switch (((MessageObject) obj).getType()) {
                case REQ_TOKEN:
                    ReqToken reqToken = (ReqToken) obj;
                    String token = new StartupMessage(
                            reqToken.getMacAddress(), reqToken.getAppCode()
                    ).getServiceToken();
                    ChannelManager.getInstance().bind(ctx.getChannel(), token);
                    resp = MessageObjects.respToken(token);
                    break;
            }
            if (resp != null) {
                ctx.getChannel().write(resp);
            }
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