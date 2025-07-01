package com.dmoser.codyssey.bragi.snapcast.api.request.stream;

import com.dmoser.codyssey.bragi.snapcast.api.model.stream.Properties;
import com.dmoser.codyssey.bragi.snapcast.api.request.Notification;
import com.dmoser.codyssey.bragi.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

import java.util.Optional;

public record OnPropertiesNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {
    @Override
    public void process(State state) {
        state.getServer()
                .streams
                .stream()
                .filter(stream -> stream.id.equals(params.id))
                .forEach(stream -> stream.properties = Optional.of(params.properties));
    }

    public record Params(String id, Properties properties) {
    }
}
