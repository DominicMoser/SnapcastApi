package com.dmoser.codyssey.bragi.snapcast.api.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestMethod {
    SERVER_GET_RPC_VERSION("Server.GetRPCVersion"),
    SERVER_GET_STATUS("Server.GetStatus"),
    SERVER_DELETE_CLIENT("DeleteClient"),

    GROUP_GET_STATUS("Group.GetStatus"),
    GROUP_SET_CLIENTS("Group.SetClients"),
    GROUP_SET_MUTE("Group.SetMute"),
    GROUP_SET_NAME("Group.SetName"),
    GROUP_SET_STREAM("Group.SetStream"),

    STREAM_CONTROL("Stream.Control"),
    STREAM_SET_PROPERTY("Stream.SetProperty"),
    STREAM_ADD("Stream.AddStream"),
    STREAM_REMOVE("Stream.RemoveStream"),

    CLIENT_GET_STATUS("Client.GetStatus"),
    CLIENT_SET_VOLUME("Client.SetVolume"),
    CLIENT_SET_LATENCY("Client.SetLatency"),
    CLIENT_SET_NAME("Client.SetName");

    final String method;

    RequestMethod(String requestMethod) {
        this.method = requestMethod;
    }

    @Override
    @JsonValue
    public String toString() {
        return method;
    }
}
