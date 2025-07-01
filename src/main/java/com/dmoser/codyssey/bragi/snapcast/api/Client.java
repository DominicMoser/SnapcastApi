package com.dmoser.codyssey.bragi.snapcast.api;

import com.dmoser.codyssey.bragi.snapcast.api.request.client.SetLatency;
import com.dmoser.codyssey.bragi.snapcast.api.request.client.SetName;
import com.dmoser.codyssey.bragi.snapcast.api.request.client.SetVolume;
import com.dmoser.codyssey.bragi.snapcast.api.service.Communication;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

import java.util.List;

public class Client extends ApiEndpoint {
    public Client(Communication communication, State state) {
        super(communication, state);
    }

    /**
     * Sets the name of the client.
     *
     * @param id The id of this client.
     * @since 1.0.0
     */
    public void setName(String id, String name) {
        communication.sendRequest(new SetName.Request(id, name));
    }

    /**
     * Check if this client is connected to the server.
     *
     * @return True when the client is connected.
     * @since 1.0.0
     */
    public boolean isConnected(String id) {
        var client = state.getClient(id);
        return client.map(value -> value.connected).orElse(false);
    }

    /**
     * Get the latency of the current client.
     *
     * @return The latency in ms.
     * @since 1.0.0
     */
    public int getLatency(String id) {
        var client = state.getClient(id);
        return client.map(value -> value.config.latency).orElse(0);
    }

    /**
     * Sets the latency of this client.
     *
     * @param latency The new latency of this client.
     * @since 1.0.0
     */
    public void setLatency(String id, int latency) {
        communication.sendRequest(new SetLatency.Request(id, latency));
    }

    /**
     * Checks if this client is currently muted.
     *
     * @return True when this client is muted.
     * @since 1.0.0
     */
    public boolean isMuted(String id) {
        var client = state.getClient(id);
        return client.map(value -> value.config.volume.muted()).orElse(false);
    }

    public void setMuted(String id, boolean setMuted) {
        var clientOptional = state.getClient(id);
        if (clientOptional.isEmpty()) {
            return;
        }
        var client = clientOptional.get();
        communication.sendRequest(new SetVolume.Request(id, setMuted, client.config.volume.percent()));
    }

    /**
     * Get the volume at which this client is playing music at.
     *
     * @return The current volume as an integer in the range from 0 to 100%.
     * @since 1.0.0
     */
    public int getVolume(String id) {
        return state.getClient(id).map(client -> client.config.volume.percent()).orElse(0);
    }

    /**
     * Sets the volume of this client. The value can value between 0-100 where 0 is silent and 100 is the maximum
     * volume.
     *
     * @param percentage The new volume.
     * @since 1.0.0
     */
    public void setVolume(String id, int percentage) {
        var clientOptional = state.getClient(id);
        if (clientOptional.isEmpty()) {
            return;
        }
        var client = clientOptional.get();
        communication.sendRequest(new SetVolume.Request(id, client.config.volume.muted(), percentage));
    }

    /**
     * Get all {@link Client}'s of this server.
     *
     * @return all clients.
     * @since 2.0.0
     */
    public List<com.dmoser.codyssey.bragi.snapcast.api.model.group.Client> getAllClients() {
        return state.getGroups()
                .stream()
                .flatMap(group -> group.clients.stream())
                .toList();
    }

}
