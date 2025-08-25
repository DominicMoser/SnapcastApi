package com.dmoser.codyssey.snapcast.api.model.stream.properties;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PlaybackStatus {
    PLAYING("playing"),
    STOPPED("stopped"),
    PAUSED("paused");

    final String status;

    PlaybackStatus(String playbackStatusString) {
        this.status = playbackStatusString;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }
}
