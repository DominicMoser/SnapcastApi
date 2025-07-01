package com.dmoser.codyssey.bragi.snapcast.api.model;

import com.dmoser.codyssey.bragi.snapcast.api.model.group.Client;

import java.util.List;

public class Group {
    public List<Client> clients;
    public String id;
    public Boolean muted;
    public String name;
    public String stream_id;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Group group) {
            return group.id.equals(id);
        }
        return false;
    }
}
