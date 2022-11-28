package de.leandersteiner.producer.infrastructue.messaging;

public interface MessageProducer {
    void send(String channel, String payload);
}
