package com.rockey.relax.akka.message;

import java.util.Optional;

public interface FuseReviveMessage {

    long getId();

    Optional<?> getPayload();
}
