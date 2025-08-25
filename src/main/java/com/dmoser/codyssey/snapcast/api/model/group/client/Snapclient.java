package com.dmoser.codyssey.snapcast.api.model.group.client;

public record Snapclient(
        String name,
        int protocolVersion,
        String version
) {
}
