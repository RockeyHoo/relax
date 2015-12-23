/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package com.rockey.relax.example;

import com.rockey.relax.akka.actor.FuseEndpointActor;
import com.rockey.relax.akka.message.FuseRequestMessage;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-12-23
 * Project        : example
 * File Name      : HelloWorld.java
 */
public class HelloWorld extends FuseEndpointActor
{

    @Override
    protected void onRequest(FuseRequestMessage request)
    {
        proto.respond(request, "Hello World !\n");
    }

}
