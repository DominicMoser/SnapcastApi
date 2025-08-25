package com.dmoser.codyssey.snapcast.api.model.stream;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    IDLE("idle"),
    PLAYING("playing");

    final String status;

    Status(String statusString) {
        this.status = statusString;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }
}
