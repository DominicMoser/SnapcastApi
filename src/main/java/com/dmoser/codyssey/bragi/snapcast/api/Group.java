package com.dmoser.codyssey.bragi.snapcast.api;

import java.util.List;

/**
 * Groups are the major control instance of snapcast. Clients can be added to a group to play the stream associated to
 * the group.
 *
 * @author dominic@dmoser.dev
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface Group {

    /**
     * Get the id of this group.
     *
     * @return The id.
     * @since 1.0.0
     */
    String getId();

    /**
     * Get the name of this group.
     *
     * @return The name.
     * @since 1.0.0
     */
    String getName();

    /**
     * Set the name of this group.
     *
     * @param newName The new name.
     * @since 1.0.0
     */
    void setName(String newName);

    /**
     * Get all {@link Client}'s which are part of this group.
     *
     * @return A list of clients.
     * @since 1.0.0
     */
    List<Client> getClients();

    /**
     * Replaces all existing {@link Client}'s with those of the list. Clients which where part of this group, but are
     * not in this list will be removed from the group.
     *
     * @param clients The new clients.
     * @since 1.0.0
     */
    void setClients(List<Client> clients);

    /**
     * Adds a new {@link Client} to this group without removing old ones.
     *
     * @param client The new client.
     * @since 1.0.0
     */
    void addClient(Client client);

    /**
     * Checks if this group is currently muted.
     *
     * @return True when this group is muted.
     * @since 1.0.0
     */
    boolean isMuted();

    /**
     * Sets if this group is muted or unmuted.
     *
     * @param mute True when this group should be muted.
     * @since 1.0.0
     */
    void setMuted(boolean mute);

    /**
     * Get the {@link Stream} which this group is currently playing.
     *
     * @return The current stream.
     * @since 1.0.0
     */
    Stream getStream();

    /**
     * Sets the {@link Stream} this group is currently playing to a different stream.
     *
     * @param stream The new stream.
     * @since 1.0.0
     */
    void setStream(Stream stream);

    /**
     * Get the average volume of all clients.
     *
     * @return The average volume.
     * @since 1.0.0
     */
    int getVolume();

    /**
     * Sets the volume of all clients in this group.
     *
     * @param percentage The volume in percentage a percentage of the total volume.
     */
    void setVolume(int percentage);

}
