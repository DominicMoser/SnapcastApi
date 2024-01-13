package com.dmoser.codyssey.snapcast.api;

/**
 * Represents a stream.
 *
 * @author dominic@dmoser.dev
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface Stream {

    /**
     * Get the name of this stream.
     *
     * @return the name.
     * @since 1.0.0
     */
    String getName();

    /**
     * Get the id of this stream.
     *
     * @return The id.
     * @since 1.0.0
     */
    String getId();

}
