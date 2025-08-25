package com.dmoser.codyssey.snapcast.api.model.server;

public record Snapserver(
        int controlProtocolVersion,
        String name,
        String protocolVersion,
        String version
) {
}
