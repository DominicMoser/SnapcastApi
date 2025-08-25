package com.dmoser.codyssey.snapcast.api.request.group;

import com.dmoser.codyssey.snapcast.api.request.Notification;
import com.dmoser.codyssey.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.snapcast.api.service.State;

public record OnStreamChangedNotification(
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
                .forEach(group -> group.stream_id = params.stream_id);
    }

    public record Params(String id, String stream_id) {
    }
}
