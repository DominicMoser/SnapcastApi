package com.dmoser.codyssey.snapcast.api.request.stream;

import com.dmoser.codyssey.snapcast.api.model.Stream;
import com.dmoser.codyssey.snapcast.api.request.Notification;
import com.dmoser.codyssey.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.snapcast.api.service.State;

public record OnUpdateNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {

    @Override
    public void process(State state) {
        state.update(params.stream);
    }

    public record Params(String id, Stream stream) {
    }
}
