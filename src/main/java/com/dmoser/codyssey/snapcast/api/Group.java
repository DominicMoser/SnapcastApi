package com.dmoser.codyssey.snapcast.api;

import com.dmoser.codyssey.snapcast.api.model.Stream;
import com.dmoser.codyssey.snapcast.api.request.client.SetVolume;
import com.dmoser.codyssey.snapcast.api.request.group.SetClients;
import com.dmoser.codyssey.snapcast.api.request.group.SetMute;
import com.dmoser.codyssey.snapcast.api.request.group.SetName;
import com.dmoser.codyssey.snapcast.api.request.group.SetStream;
import com.dmoser.codyssey.snapcast.api.service.Communication;
import com.dmoser.codyssey.snapcast.api.service.State;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Group extends ApiEndpoint {
    public Group(Communication communication, State state) {
        super(communication, state);
    }

    /**
     * Get the name of this group.
     *
     * @return The name.
     * @since 1.0.0
     */
    public String getName(String id) {
        var groupOptional = state.getGroup(id);
        if (groupOptional.isEmpty()) {
            return "";
        }
        var group = groupOptional.get();
        return group.name;
    }

    /**
     * Set the name of this group.
     *
     * @param newName The new name.
     * @since 1.0.0
     */
    public void setName(String groupId, String newName) {
        String id = state.getGroup(groupId).map(group -> group.id).orElse("");
        communication.sendRequest(new SetName.Request(id, newName));
    }

    /**
     * Get all {@link Client}'s which are part of this group.
     *
     * @return A list of clients.
     * @since 1.0.0
     */
    public List<com.dmoser.codyssey.snapcast.api.model.group.Client> getClients(String groupId) {
        return state.getGroup(groupId).map(group -> group.clients).orElse(List.of());
    }

    public void setClients(String groupId, Set<String> clientIds) {
        var group = state.getGroup(groupId);
        var clients = clientIds.stream()
                .map(state::getClient)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(client -> client.id)
                .collect(Collectors.toSet());
        group.ifPresent(g -> communication.sendRequest(new SetClients.Request(g.id, clients)));

    }

    public void addClient(String groupId, String clientId) {

        Set<String> clientIds = state.getGroup(groupId)
                .map(group -> group.clients)
                .orElse(List.of())
                .stream()
                .map(client -> client.id)
                .collect(Collectors.toSet());

        clientIds.add(clientId);

        communication.sendRequest(new SetClients.Request(groupId, clientIds));
    }

    /**
     * Checks if this group is currently muted.
     *
     * @return True when this group is muted.
     * @since 1.0.0
     */
    public boolean isMuted(String groupId) {
        return state.getGroup(groupId)
                .map(group -> group.muted)
                .orElse(true);
    }

    /**
     * Sets if this group is muted or unmuted.
     *
     * @param mute True when this group should be muted.
     * @since 1.0.0
     */
    public void setMuted(String groupId, boolean mute) {
        communication.sendRequest(new SetMute.Request(groupId, mute));
    }

    public Stream getStream(String groupId) {
        // TODO Error handling
        return state.getGroup(groupId)
                .map(group -> group.stream_id)
                .map(state::getStream)
                .get()
                .get();
    }

    public void setStream(String groupId, String streamId) {
        state.getGroup(groupId).ifPresent((g) -> communication.sendRequest(new SetStream.Request(g.id, streamId)));
    }

    /**
     * Get the average volume of all clients.
     *
     * @return The average volume.
     * @since 1.0.0
     */
    public int getVolume(String groupId) {
        return state.getGroup(groupId)
                .map(group -> group.clients
                        .stream()
                        .filter(client -> client.connected)
                        .map(client -> client.config.volume.percent())
                        .findFirst().orElse(0))
                .orElse(0);
    }

    /**
     * Sets the volume of all clients in this group.
     *
     * @param percentage The volume in percentage a percentage of the total volume.
     */
    public void setVolume(String groupId, int percentage) {
        state.getGroup(groupId)
                .stream()
                .flatMap(group -> group.clients.stream())
                .forEach(client -> communication.sendRequest(
                        new SetVolume.Request(
                                client.id,
                                client.config.volume.muted(),
                                percentage
                        )));
    }

    /**
     * Get all {@link Group}'s of this server which are currently active. Active means, that there needs to be at least
     * one {@link Client} in this group which is currently connected.
     *
     * @return All active groups.
     * @since 1.0.0
     */
    public List<com.dmoser.codyssey.snapcast.api.model.Group> getActiveGroups() {
        return state.getGroups()
                .stream()
                .filter(
                        group -> group.clients.stream().anyMatch(client -> client.connected))
                .toList();
    }

    /**
     * Get all {@link com.dmoser.codyssey.snapcast.api.model.Group}'s of this server.
     *
     * @return all groups.
     * @since 1.0.0
     */
    public List<com.dmoser.codyssey.snapcast.api.model.Group> getAllGroups() {
        return state.getGroups();
    }

}
