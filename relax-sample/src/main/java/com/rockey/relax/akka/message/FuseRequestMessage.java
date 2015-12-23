package com.rockey.relax.akka.message;

import com.rockey.relax.config.route.Route;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Optional;

public interface FuseRequestMessage extends Route
{

    long getId();

    HttpRequest getRequest();

    String getRequestBody();

    ChannelHandlerContext getChannelContext();

    void flush();

    boolean flushed();

    public <T> Optional<T> getParam(String name, Class<T> clazz);

}
