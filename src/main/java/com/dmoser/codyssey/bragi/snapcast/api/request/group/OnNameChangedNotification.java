package com.dmoser.codyssey.bragi.snapcast.api.request.group;

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
                .filter(group -> group.id.equals(params.id))
                .forEach(group -> group.name = params.name);

    }

    public record Params(String id, String name) {

    }
}
