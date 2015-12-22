package com.rockey.relax.akka.actor.annotated;

import com.rockey.relax.akka.actor.FuseBaseActor;
import com.rockey.relax.config.annotation.FuseActor;

@FuseActor(id = "testActor", spin = 5)
public class TestActor extends FuseBaseActor {

}
