package de.leandersteiner.producer.infrastructue.messaging.nats;

import de.leandersteiner.producer.infrastructue.messaging.MessageConsumer;
import io.nats.client.Connection;
import io.nats.client.MessageHandler;
import io.nats.client.Nats;
import io.nats.client.PushSubscribeOptions;
import io.nats.client.impl.NatsWatchSubscription;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Consumer;

public class NatsMessageConsumer implements MessageConsumer {

    private final Connection natsConnection;

    public NatsMessageConsumer(String connectionString) throws IOException, InterruptedException {
        this.natsConnection = Nats.connect(connectionString);
    }

    @Override
    public void subscribe(String channel, Consumer<String> cb) {
        MessageHandler handler = (msg) -> {
            cb.accept(new String(msg.getData(), StandardCharsets.UTF_8));
        };

        var sub = natsConnection.subscribe(channel, "queue");

        while(true) {
            try {
                var message = sub.nextMessage(Duration.ofMillis(500));
                handler.onMessage(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
