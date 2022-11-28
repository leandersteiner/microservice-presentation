package de.leandersteiner.producer.infrastructue.messaging.nats;

import de.leandersteiner.producer.infrastructue.messaging.MessageProducer;
import io.nats.client.Connection;
import io.nats.client.Nats;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class NatsMessageProducer implements MessageProducer {

    private final Connection natsConnection;

    public NatsMessageProducer(String connectionString) throws IOException, InterruptedException {
        natsConnection = Nats.connect(connectionString);
    }
    @Override
    public void send(String subject, String message) {
        natsConnection.publish(subject, message.getBytes(StandardCharsets.UTF_8));
    }
}
