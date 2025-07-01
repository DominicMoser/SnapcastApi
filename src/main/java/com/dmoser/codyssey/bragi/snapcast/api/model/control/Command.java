package com.dmoser.codyssey.bragi.snapcast.api.model.control;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Command {
    PLAY("play"),
    PAUSE("pause"),
    PLAY_PAUSE("playPause"),
    STOP("stop"),
    NEXT("next"),
    PREVIOUS("previous");

    final String command;

    Command(String commandString) {
        this.command = commandString;
    }

    @Override
    @JsonValue
    public String toString() {
        return command;
    }
}
