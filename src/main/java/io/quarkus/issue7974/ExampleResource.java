package io.quarkus.issue7974;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ExampleResource {

    @Inject
    @Channel("my-channel")
    public Emitter<Integer> emitter;

    public void emitSenseOfLife() {
        emitter.send(42);
    }

    public void emitLuckyNumber() {
        emitter.send(13);
    }
}