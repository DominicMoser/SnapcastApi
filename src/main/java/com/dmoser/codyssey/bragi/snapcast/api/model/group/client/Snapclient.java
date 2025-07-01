package com.dmoser.codyssey.bragi.snapcast.api.model.group.client;

public record Snapclient(
        String name,
        int protocolVersion,
        String version
) {
}
