package com.dmoser.codyssey.bragi.snapcast.api.request.group;

import com.dmoser.codyssey.bragi.snapcast.api.model.Group;
import com.dmoser.codyssey.bragi.snapcast.api.request.Notification;
import com.dmoser.codyssey.bragi.snapcast.api.request.NotificationMethod;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public record OnMuteNotification(
        String jsonrpc,
        NotificationMethod method,
        Params params
) implements Notification {

    @Override
    public void process(State state) {

        var groupOptional = state.getGroup(params.id);
        if (groupOptional.isPresent()) {
            Group group = groupOptional.get();
            group.muted = params.mute;
            state.update(group);

        }

    }

    public record Params(String id, boolean mute) {
    }
}
