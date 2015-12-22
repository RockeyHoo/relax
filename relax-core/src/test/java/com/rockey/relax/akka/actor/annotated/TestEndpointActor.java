package com.rockey.relax.akka.actor.annotated;

import com.rockey.relax.akka.actor.FuseEndpointActor;
import com.rockey.relax.config.annotation.FuseEndpoint;

@FuseEndpoint( path ="/test", method = "GET")
public class TestEndpointActor extends FuseEndpointActor
{

}
