package cn.mobiledaily.domain.mobile.socketserver;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelLocal;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Component
public final class Server {
    private static final Timer TIMER = new HashedWheelTimer();
    @Value("${mobile.socket.port}")
    private int port;
    @Value("${mobile.socket.host}")
    private String host;
    private ServerBootstrap bootstrap;

    @PostConstruct
    public void start() {
        bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline result = new DefaultChannelPipeline();
                result.addLast("encode", new StringEncoder());
                result.addLast("decode", new StringDecoder());
                result.addLast("handler", new ServerHandler());
                result.addLast("timeout", new IdleStateHandler(TIMER, 10, 10, 0));
                result.addLast("idleStateHandler", new IdleStateAwareChannelHandler());
                return result;
            }
        });
        bootstrap.bind(new InetSocketAddress(host, port));
    }

    @PreDestroy
    public void stop() {
        bootstrap.releaseExternalResources();
    }
}

