package com.rockey.relax.config.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.rockey.relax.akka.actor.ActorSystemAware;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public interface ActorFactory extends ActorSystemAware, ApplicationContextAware {

    public Optional<ActorRef> getLocalActor(String actorClass);
    
    public Optional<ActorRef> getLocalActor(String actorClass, String actorName);
    
    public Optional<ActorRef> getLocalActor(String ref, String actorClass, int spinCount);
    
    public Optional<ActorRef> getLocalActor(String ref, String actorClass, String actorName, int spinCount);
    
    public Optional<ActorRef> getLocalActorByRef(String ref);

    public Optional<ActorSelection> select(String path);

    public Iterable<ActorRef> getRoutees(String ref, Class<?> clazz, int spinCount);

    
}
