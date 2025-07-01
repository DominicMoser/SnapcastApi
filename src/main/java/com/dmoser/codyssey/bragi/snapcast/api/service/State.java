package com.dmoser.codyssey.bragi.snapcast.api.service;

import com.dmoser.codyssey.bragi.snapcast.api.model.Group;
import com.dmoser.codyssey.bragi.snapcast.api.model.Server;
import com.dmoser.codyssey.bragi.snapcast.api.model.Stream;
import com.dmoser.codyssey.bragi.snapcast.api.model.group.Client;
import com.dmoser.codyssey.bragi.snapcast.api.request.server.GetStatus;

import java.util.List;
import java.util.Optional;

public class State {

    private Server server = null;
    private Communication communication;

    private State() {

    }

    public static State create() {
        // TODO initialize serverstate
        return new State();
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    @Deprecated
    public Server getServer() {
        return server;
    }

    public void update(Server server) {
        this.server = server;
    }

    public void update(Client client) {
        for (Group group : server.groups) {
            if (group.clients.contains(client)) {
                group.clients.remove(client);
                group.clients.add(client);
                return;
            }
        }
    }

    public void update(Group group) {
        server.groups.remove(group);
        server.groups.add(group);
    }

    public void update(Stream stream) {
        server.streams.remove(stream);
        server.streams.add(stream);
    }

    public void update() {
        if (communication == null) {
            return;
        }
        communication.sendRequest(new GetStatus.Request());
    }

    public Optional<Client> getClient(String id) {
        Optional<Client> clientOptional = server.groups
                .stream()
                .flatMap(group -> group.clients.stream())
                .filter(client -> client.id.equals(id))
                .findFirst();
        if (clientOptional.isEmpty()) {
            clientOptional = server.groups
                    .stream()
                    .flatMap(group -> group.clients.stream())
                    .filter(client -> client.config.name.equals(id))
                    .findFirst();
        }
        return clientOptional;
    }

    public Optional<Group> getGroup(String id) {
        Optional<Group> groupOptional = server.groups
                .stream()
                .filter(group -> group.id.equals(id))
                .findFirst();
        if (groupOptional.isEmpty()) {
            groupOptional = server.groups
                    .stream()
                    .filter(group -> group.name.equals(id))
                    .findFirst();
        }

        return groupOptional;
    }

    public List<Group> getGroups() {
        return server.groups;
    }

    public Optional<Stream> getStream(String id) {
        return server.streams
                .stream()
                .filter(stream -> stream.id.equals(id))
                .findFirst();
    }

    public List<Stream> getStreams() {
        return server.streams;
    }
}
