package com.dmoser.codyssey.bragi.snapcast.api.request.server;

import com.dmoser.codyssey.bragi.snapcast.api.model.Server;
import com.dmoser.codyssey.bragi.snapcast.api.request.Notification;
import com.dmoser.codyssey.bragi.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public record OnUpdateNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {

    @Override
    public void process(State state) {
        state.update(params.server);
    }

    public record Params(Server server) {
    }
}
