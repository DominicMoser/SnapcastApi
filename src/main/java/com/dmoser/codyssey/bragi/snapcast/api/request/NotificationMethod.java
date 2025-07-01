package com.dmoser.codyssey.bragi.snapcast.api.request;

import com.dmoser.codyssey.bragi.snapcast.api.request.client.OnConnectNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.client.OnDisconnectNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.client.OnLatencyNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.client.OnVolumeChangedNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.OnMuteNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.OnNameChangedNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.OnStreamChangedNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.server.OnUpdateNotification;
import com.dmoser.codyssey.bragi.snapcast.api.request.stream.OnPropertiesNotification;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum NotificationMethod {

    SERVER_ON_UPDATE("Server.OnUpdate", OnUpdateNotification.class),

    GROUP_ON_MUTE("Group.OnMute", OnMuteNotification.class),
    GROUP_ON_STREAM_CHANGED("Group.OnStreamChanged", OnStreamChangedNotification.class),
    GROUP_ON_NAME_CHANGED("Group.OnNameChanged", OnNameChangedNotification.class),

    STREAM_ON_PROPERTIES("Stream.OnProperties", OnPropertiesNotification.class),
    STREAM_ON_UPDATE("Stream.OnUpdate", com.dmoser.codyssey.bragi.snapcast.api.request.stream.OnUpdateNotification.class),

    CLIENT_ON_CONNECT("Client.OnConnect", OnConnectNotification.class),
    CLIENT_ON_DISCONNECT("Client.OnDisconnect", OnDisconnectNotification.class),
    CLIENT_ON_VOLUME_CHANGED("Client.OnVolumeChanged", OnVolumeChangedNotification.class),
    CLIENT_ON_LATENCY_CHANGED("Client.OnLatencyChanged", OnLatencyNotification.class),
    CLIENT_ON_NAME_CHANGED("Client.OnNameChanged", com.dmoser.codyssey.bragi.snapcast.api.request.client.OnNameChangedNotification.class);

    private static final Map<String, NotificationMethod> METHOD_MAP = new HashMap<>();

    static {
        for (NotificationMethod nm : values()) {
            METHOD_MAP.put(nm.method, nm);
        }
    }

    public final String method;
    public final Class<? extends Notification> notificationClass;

    NotificationMethod(String notificationMethod, Class<? extends Notification> notificationClass) {
        this.method = notificationMethod;
        this.notificationClass = notificationClass;
    }

    public static NotificationMethod fromMethod(String method) {
        NotificationMethod result = METHOD_MAP.get(method);
        if (result == null) throw new IllegalArgumentException("Unknown method: " + method);
        return result;
    }

    @Override
    @JsonValue
    public String toString() {
        return method;
    }
}