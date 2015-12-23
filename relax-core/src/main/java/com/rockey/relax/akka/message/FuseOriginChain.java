package com.rockey.relax.akka.message;

import akka.actor.ActorRef;

import java.util.Deque;
import java.util.Optional;

public interface FuseOriginChain {

    Optional<ActorRef> popOrigin();
    
    void pushOrigin(ActorRef actorRef);

    Deque<ActorRef> getChain();
}
