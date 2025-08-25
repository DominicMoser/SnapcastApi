package com.dmoser.codyssey.snapcast.api.model.stream.properties;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LoopStatus {
    NONE("none"),
    TRACK("track"),
    PLAYLIST("playlist");

    final String status;

    LoopStatus(String playbackStatusString) {
        this.status = playbackStatusString;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }
}
