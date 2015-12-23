package com.rockey.relax.codec;

import com.rockey.relax.akka.message.FuseRequestMessage;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public interface WireProtocol {
    
    void ok(FuseRequestMessage message);


    void respond(FuseRequestMessage message, Object object);
    
    void respond(FuseRequestMessage message, String content);
    
    void respondRaw(FuseRequestMessage message, HttpResponseStatus status, ByteBuffer data, Map<String, String> headers);

    
    void error(FuseRequestMessage message);
    
    void error(FuseRequestMessage message, Object object);

    void error(FuseRequestMessage message, String content);
    
    void error(FuseRequestMessage message, HttpResponseStatus status, Object object);

    void error(FuseRequestMessage message, HttpResponseStatus status, String content);
    

    <T> Optional<T> read(FuseRequestMessage message, Class<T> clazz);

    default void stream(FuseRequestMessage message, Path path) {

        if (!message.flushed()) {
            ChannelHandlerContext ctx = message.getChannelContext();
            HttpResponse response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);

            long size = WireProtocol.getFileSize(path);

            response.headers().set(CONTENT_LENGTH, size);

            ctx.write(response);

            try {
                ctx.write(
                    new DefaultFileRegion(FileChannel.open(path), 0, size)
                );

                ChannelFuture cfuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

                if (!isKeepAlive(message.getRequest())) {
                    cfuture.addListener(ChannelFutureListener.CLOSE);
                }
            }
            catch (Exception ex) {
                log.error("Error streaming file !", ex);
                ctx.close();
            }
        }
    }

    static long getFileSize(Path path) {
        try {
            return Files.size(path);
        }
        catch (Exception ex) {
            log.warn("Error getting file size ! {}", path, ex);
            return -1;
        }
    }

    static final Logger log = LoggerFactory.getLogger(WireProtocol.class);
}
