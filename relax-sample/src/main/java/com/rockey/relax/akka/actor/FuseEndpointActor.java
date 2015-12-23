package com.rockey.relax.akka.actor;

import java.lang.reflect.Method;
import java.util.Optional;

import com.rockey.relax.codec.WireProtocol;
import com.rockey.relax.config.route.RouteHandler;
import com.rockey.relax.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rockey.relax.akka.message.FuseRequestMessage;
import com.rockey.relax.metrics.MetricsRegistry;

public abstract class FuseEndpointActor extends FuseBaseActor {
    
    @Autowired protected MetricsRegistry metrics;
    
    public FuseEndpointActor() {
        super();
    }

    // TODO: refactor metrics
    //            meter = metrics.getRegistry().timer(getClass().getName());

    @Override
    public void onReceive(Object message) throws Exception {
        try {
            if (message instanceof FuseRequestMessage) {
                onRequest((FuseRequestMessage) message);
                //proto.error((FuseRequestMessage) message);
            }
            else {
                super.onReceive(message);
            }
        }
        catch (Exception ex) {
            log.error("Error handling request !", ex);
            unhandled(message);
        }
        finally {
            // TODO: context flush
        }
    }
    
    protected void onRequest(final FuseRequestMessage request) {
        
        RouteHandler rhandler = request.getHandler();
        
        Optional<String> method = rhandler.getMethodName();
        
        if (method.isPresent()) {
            // Invoke configured method instead of default 'onReceive'. Method needs to have correct
            // signature, otherwise, fallback to 'onReceive' will occur.
            //
            Optional<Method> target = Tools.lookupMethod(this, method.get());
            if (target.isPresent()) {
                try {
                    target.get()
                          .invoke(this, request);
                }
                catch (Exception ex) {
                    log.warn("[fuse] Invocation failure. x_x", ex);
                }
            }       
        }
        else {
            log.warn("[fuse] No handling method specified. Override onReceive. x_x");
        }
    }



    @Override
    public void unhandled(Object message) {
        super.unhandled(message);
        if (message instanceof FuseRequestMessage) {
            proto.error((FuseRequestMessage) message);
        }
    }

    public void setProto(WireProtocol proto) {
        this.proto = proto;
    }

    protected static final Logger log = LoggerFactory.getLogger(FuseEndpointActor.class);
}
