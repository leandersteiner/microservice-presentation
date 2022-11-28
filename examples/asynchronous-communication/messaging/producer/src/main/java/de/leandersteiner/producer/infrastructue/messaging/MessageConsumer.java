package de.leandersteiner.producer.infrastructue.messaging;

import java.util.function.Consumer;

public interface MessageConsumer {
    void subscribe(String channel, Consumer<String> cb);
}
