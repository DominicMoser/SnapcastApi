package com.dmoser.codyssey.bragi.snapcast.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * Represents the snapserver instance running at a given url.
 *
 * @author dominic@dmoser.dev
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface Server {

    /**
     * Message for the Exception when no server builder implementation exists.
     */
    String NO_BUILDER_INTERFACE_FOUND = "No implementation of the Server.BuilderInterface was" +
            " found. " +
            "Please add a module containing an implementation.";

    /**
     * Use the service loader to get the loaded implementation of the snapcast api.
     *
     * @return A builder for the server class from the loaded implementation.
     */
    static ServerBuilder Builder() {
        return ServiceLoader.load(ServerBuilder.class).findFirst().orElseThrow(() -> new NoSuchElementException(NO_BUILDER_INTERFACE_FOUND));
    }

    /**
     * Get all {@link Group}'s of this server which are currently active. Active means, that there needs to be at least
     * one {@link Client} in this group which is currently connected.
     *
     * @return All active groups.
     * @since 1.0.0
     */
    List<Group> getActiveGroups();

    /**
     * Get all {@link Group}'s of this server.
     *
     * @return all groups.
     * @since 1.0.0
     */
    List<Group> getAllGroups();

    /**
     * Get all {@link Stream}'s of this server.
     *
     * @return all streams.
     * @since 1.0.0
     */
    List<Stream> getAllStreams();

    /**
     * Creates a new {@link Stream}.
     *
     * @param streamConstructionMsg The msg containing all information to create this stream.
     * @return The id of the stream.
     * @since 2.0.0
     */
    String createNewStream(Stream.StreamBuilder.StreamConstructionMsg streamConstructionMsg);

    /**
     * Removes a stream.
     *
     * @param stream The stream to remove.
     * @since 2.0.0
     */
    void removeStream(Stream stream);

    /**
     * Get all {@link Client}'s of this server.
     *
     * @return all clients.
     * @since 2.0.0
     */
    List<Client> getAllClients();

}
