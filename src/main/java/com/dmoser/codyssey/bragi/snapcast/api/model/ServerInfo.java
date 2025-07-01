package com.dmoser.codyssey.bragi.snapcast.api.model;

import com.dmoser.codyssey.bragi.snapcast.api.model.server.Host;
import com.dmoser.codyssey.bragi.snapcast.api.model.server.Snapserver;

public record ServerInfo(
        Host host,
        Snapserver snapserver
) {
}
