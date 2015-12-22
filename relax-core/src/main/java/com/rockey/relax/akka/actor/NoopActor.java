package com.rockey.relax.akka.actor;

import com.rockey.relax.akka.message.FuseRequestMessage;

public class NoopActor extends FuseEndpointActor {

    @Override
    protected void onRequest(FuseRequestMessage message) {
        
    }

}
