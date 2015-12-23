package com.rockey.relax.akka.async;


import com.rockey.relax.akka.message.FuseInternalMessage;

import java.util.Optional;

public interface RequestSuspender {

    void suspend(FuseInternalMessage message);

    Optional<FuseInternalMessage> revive(long id);

}
