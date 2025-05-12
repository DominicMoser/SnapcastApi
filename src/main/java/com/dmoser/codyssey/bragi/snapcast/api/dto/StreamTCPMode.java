package com.dmoser.codyssey.bragi.snapcast.api.dto;

public enum StreamTCPMode {
    CLIENT("client", "server"),
    SERVER("server", "client");

    private final String streamMode;
    private final String snapserverMode;

    StreamTCPMode(String streamMode, String snapserverMode) {
        this.streamMode = streamMode;
        this.snapserverMode = snapserverMode;
    }

    public String getSnapserverMode() {
        return this.snapserverMode;
    }

    public String getStreamMode() {
        return this.streamMode;
    }
}
