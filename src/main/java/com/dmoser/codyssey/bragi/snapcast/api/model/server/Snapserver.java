package com.dmoser.codyssey.bragi.snapcast.api.model.server;

public record Snapserver(
        int controlProtocolVersion,
        String name,
        String protocolVersion,
        String version
) {
}
