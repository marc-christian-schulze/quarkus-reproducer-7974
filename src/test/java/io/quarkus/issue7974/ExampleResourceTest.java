package io.quarkus.issue7974;

import io.quarkus.test.junit.QuarkusTest;
import io.reactivex.subscribers.TestSubscriber;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;

import javax.inject.Inject;

@QuarkusTest
public class ExampleResourceTest {

    @Inject
    @Channel("my-channel")
    public Publisher<Integer> publisher;

    @Inject
    public ExampleResource resource;

    private TestSubscriber<Integer> subscriber;

    @BeforeEach
    public void setUp() {
        subscriber = new TestSubscriber<>();
        publisher.subscribe(subscriber);
    }

    @AfterEach
    public void tearDown() {
        subscriber.dispose();
    }

    @Test
    public void testSenseOfLife() {
        resource.emitSenseOfLife();
        subscriber.assertValue(42);
    }

    @Test
    public void testLuckyNumber() {
        resource.emitLuckyNumber();
        subscriber.assertValue(13);
    }

}