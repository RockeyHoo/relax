package com.rockey.relax.akka.actor;


import com.rockey.relax.akka.message.FuseInternalMessage;

public class ChannelClosingActor extends FuseBaseActor {

    @Override
    public void onMessage(FuseInternalMessage message) {
        message.getContext()
               .getRequest()
               .ifPresent(
                   req -> {
                       req.getChannelContext().close();
                   }
               );
    }
}
