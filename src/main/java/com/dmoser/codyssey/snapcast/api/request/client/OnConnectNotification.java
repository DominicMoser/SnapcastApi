package com.dmoser.codyssey.snapcast.api.request.client;

import com.dmoser.codyssey.snapcast.api.model.group.Client;
import com.dmoser.codyssey.snapcast.api.request.Notification;
import com.dmoser.codyssey.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.snapcast.api.service.State;

public record OnConnectNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {

    @Override
    public void process(State state) {
        state.update(params.client);
    }


    public record Params(Client client, String id) {
    }
}
