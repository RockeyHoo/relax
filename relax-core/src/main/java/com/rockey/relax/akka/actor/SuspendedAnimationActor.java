package com.rockey.relax.akka.actor;

import com.rockey.relax.akka.async.RequestSuspender;
import com.rockey.relax.akka.message.FuseInternalMessage;
import com.rockey.relax.akka.message.FuseReviveMessage;
import com.rockey.relax.akka.message.FuseSuspendMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SuspendedAnimationActor extends FuseBaseActor {

    @Autowired RequestSuspender suspender;

    @Override
    public void onMessage(FuseInternalMessage message) {

        if (message instanceof FuseSuspendMessage) {
            suspender.suspend(message);
        }
        else
        if (message instanceof FuseReviveMessage) {

            FuseReviveMessage msg = (FuseReviveMessage) message;

            // attempt revival
            Optional<FuseInternalMessage> suspended = suspender.revive(msg.getId());
            suspended.ifPresent(
                sval -> {
                    // send suspended message back towards origin; add payload
                    //
                    sval.getContext()
                        .put(
                            "payload",
                            msg.getPayload()
                        );

                    sval.popOrigin()
                        .get()
                        .tell(
                            sval,
                            self()
                        );
                }
            );
        }
    }
}
