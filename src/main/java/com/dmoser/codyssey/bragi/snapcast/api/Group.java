package com.dmoser.codyssey.bragi.snapcast.api;

import com.dmoser.codyssey.bragi.snapcast.api.request.client.SetVolume;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.SetClients;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.SetMute;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.SetName;
import com.dmoser.codyssey.bragi.snapcast.api.request.group.SetStream;
import com.dmoser.codyssey.bragi.snapcast.api.service.Communication;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

import java.util.List;
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
    public List<com.dmoser.codyssey.bragi.snapcast.api.model.group.Client> getClients(String groupId) {
        return state.getGroup(groupId).map(group -> group.clients).orElse(List.of());
    }

    /**
     * Replaces all existing {@link Client}'s with those of the list. Clients which where part of this group, but are
     * not in this list will be removed from the group.
     *
     * @param clients The new clients.
     * @since 1.0.0
     */
    public void setClients(String groupId, Set<String> clientIds) {
        communication.sendRequest(new SetClients.Request(groupId, clientIds));
    }

    /**
     * Adds a new {@link Client} to this group without removing old ones.
     *
     * @param client The new client.
     * @since 1.0.0
     */
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

    /**
     * Get the {@link com.dmoser.codyssey.bragi.snapcast.api.old.Stream} which this group is currently playing.
     *
     * @return The current stream.
     * @since 1.0.0
     */
    public com.dmoser.codyssey.bragi.snapcast.api.model.Stream getStream(String groupId) {
        // TODO Error handling
        return state.getGroup(groupId)
                .map(group -> group.stream_id)
                .map(state::getStream)
                .get()
                .get();
    }

    /**
     * Sets the {@link com.dmoser.codyssey.bragi.snapcast.api.old.Stream} this group is currently playing to a different stream.
     *
     * @param stream The new stream.
     * @since 1.0.0
     */
    public void setStream(String groupId, String streamId) {
        communication.sendRequest(new SetStream.Request(groupId, streamId));
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
    public List<com.dmoser.codyssey.bragi.snapcast.api.model.Group> getActiveGroups() {
        return state.getGroups()
                .stream()
                .filter(
                        group -> group.clients.stream().anyMatch(client -> client.connected))
                .toList();
    }

    /**
     * Get all {@link com.dmoser.codyssey.bragi.snapcast.api.model.Group}'s of this server.
     *
     * @return all groups.
     * @since 1.0.0
     */
    public List<com.dmoser.codyssey.bragi.snapcast.api.model.Group> getAllGroups() {
        return state.getGroups();
    }

}
