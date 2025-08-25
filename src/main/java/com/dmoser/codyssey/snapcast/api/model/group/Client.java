package com.dmoser.codyssey.snapcast.api.model.group;

import com.dmoser.codyssey.snapcast.api.model.group.client.Config;
import com.dmoser.codyssey.snapcast.api.model.group.client.LastSeen;
import com.dmoser.codyssey.snapcast.api.model.group.client.Snapclient;
import com.dmoser.codyssey.snapcast.api.model.server.Host;

public class Client {
    public Config config;
    public boolean connected;
    public Host host;
    public String id;
    public LastSeen lastSeen;
    public Snapclient snapclient;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client client) {
            return client.id.equals(id);
        }
        return false;
    }
}
