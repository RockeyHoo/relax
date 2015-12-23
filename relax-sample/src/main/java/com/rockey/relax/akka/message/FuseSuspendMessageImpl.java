package com.rockey.relax.akka.message;

public class FuseSuspendMessageImpl extends FuseInternalMessageImpl implements FuseSuspendMessage {

    public FuseSuspendMessageImpl(FuseRequestMessage request) {
        super(request);
    }

    public FuseSuspendMessageImpl(FuseInternalMessage message) {
        super(message);
    }
}
