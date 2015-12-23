package com.rockey.relax.akka.actor;

import org.springframework.beans.factory.annotation.Autowired;

import com.rockey.relax.FuseVersion;
import com.rockey.relax.akka.message.FuseRequestMessage;

public class ServerVersionActor extends FuseEndpointActor {

    @Autowired FuseVersion version;
    
    @Override
    protected void onRequest(FuseRequestMessage message) {
        proto.respond(message, version);
    }

}
