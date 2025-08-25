package com.dmoser.codyssey.snapcast.api.model.setProperty;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PropertyType {
    LOOP_STATUS("loopStatus"),
    SHUFFLE("shuffle"),
    VOLUME("volume"),
    MUTE("mute"),
    RATE("rate");

    final String propertyType;

    PropertyType(String propertyTypeString) {
        this.propertyType = propertyTypeString;
    }

    @Override
    @JsonValue
    public String toString() {
        return propertyType;
    }
}
