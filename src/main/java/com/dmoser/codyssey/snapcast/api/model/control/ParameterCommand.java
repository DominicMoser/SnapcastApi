package com.dmoser.codyssey.snapcast.api.model.control;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ParameterCommand {
    SEEK("seek"),
    SET_POSITION("setPosition");

    final String command;

    ParameterCommand(String commandString) {
        this.command = commandString;
    }

    @Override
    @JsonValue
    public String toString() {
        return command;
    }
}
