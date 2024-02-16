package com.dmoser.codyssey.snapcast.api.dto;

public enum StreamProtocol {
    PIPE("pipe"),
    TCP("tcp");

    final String protocol;

    StreamProtocol(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return this.protocol;
    }
}
