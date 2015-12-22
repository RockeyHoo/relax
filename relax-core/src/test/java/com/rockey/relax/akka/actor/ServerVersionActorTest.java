package com.rockey.relax.akka.actor;

import akka.testkit.TestActorRef;
import com.rockey.relax.ActorAwareTest;
import com.rockey.relax.FuseVersion;
import com.rockey.relax.akka.message.FuseRequestMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnit44Runner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnit44Runner.class)
public class ServerVersionActorTest extends ActorAwareTest {

    TestActorRef<ServerVersionActor> instance;

    @Mock FuseVersion mockVersion;

    @Before
    public void setup() {
        super.setup();

        instance = mockEndpoint(ServerVersionActor.class);
        instance.underlyingActor().version = mockVersion;
    }

    @Test
    public void testGetVersion() {

        // given
        FuseRequestMessage mockMessage = mock(FuseRequestMessage.class);

        // when
        instance.tell(mockMessage, null);

        // then
        verify(instance.underlyingActor().proto, times(1))
            .respond(eq(mockMessage), eq(mockVersion));
    }
}
