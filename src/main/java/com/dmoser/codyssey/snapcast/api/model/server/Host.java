package com.dmoser.codyssey.snapcast.api.model.server;

public record Host(
        String arch,
        String ip,
        String mac,
        String name,
        String os
) {
}
