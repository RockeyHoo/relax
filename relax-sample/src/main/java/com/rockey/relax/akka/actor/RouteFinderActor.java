package com.rockey.relax.akka.actor;

import com.rockey.relax.config.route.Route;
import io.netty.handler.codec.http.HttpMethod;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rockey.relax.akka.message.FuseRequestMessage;
import com.rockey.relax.akka.message.FuseRequestMessageImpl;
import com.rockey.relax.config.route.RoutesConfig;


public class RouteFinderActor extends FuseEndpointActor {

    @Autowired protected RoutesConfig routes;
    
    @Override
    protected void onRequest(final FuseRequestMessage message) {
        
        String uri = message.getRequest().getUri();
        
        Optional<Route> route = routes.getFuseRoute(uri);

        if (route.isPresent()) {
            Route rte = route.get();

            // add route to the message
            ((FuseRequestMessageImpl) message).setRoute(rte);

            // pass message to handling actor
            rte.getHandler()
               .getActor()
               .ifPresent(
                   handler -> {
                       HttpMethod requested = message.getRequest().getMethod();
                       HttpMethod supported = rte.getHandler().getHttpMethod();

                       if (supported.compareTo(requested) == 0) {
                           handler.tell(message, getSelf());
                       }
                       else {
                           info(requested +" not supported by " + uri.toString());
                           unhandled(message);
                       }
                   }
               );
        }
        else {
            unhandled(message);
        }
    }

    public void setRoutes(RoutesConfig routes) {
        this.routes = routes;
    }
    
}
