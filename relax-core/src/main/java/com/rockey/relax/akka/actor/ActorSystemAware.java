package com.rockey.relax.akka.actor;

import akka.actor.ActorSystem;

public interface ActorSystemAware {

    public void setActorSystem(ActorSystem actorSystem);

    public ActorSystem getActorSystem();
}
