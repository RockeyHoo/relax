package com.rockey.relax.akka.message;


public interface FuseInternalMessage extends FuseOriginChain {
    
    FuseMessageContext getContext();

    long getRequestId();

    long getTimestamp();

    FuseInternalMessage timestamp();
}
