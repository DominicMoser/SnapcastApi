package com.dmoser.codyssey.bragi.snapcast.api.request.client;

import com.dmoser.codyssey.bragi.snapcast.api.request.Notification;
import com.dmoser.codyssey.bragi.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public record OnNameChangedNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {
    @Override
    public void process(State state) {
        state.getServer()
                .groups
                .stream()
                .flatMap(group -> group.clients.stream())
                .filter(client -> client.id.equals(params.id))
                .forEach(client -> client.config.name = params.name);
    }

    public record Params(String id, String name) {
    }
}
