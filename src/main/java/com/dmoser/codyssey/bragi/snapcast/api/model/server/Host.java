package com.dmoser.codyssey.bragi.snapcast.api.model.server;

public record Host(
        String arch,
        String ip,
        String mac,
        String name,
        String os
) {
}
