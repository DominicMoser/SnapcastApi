package com.dmoser.codyssey.bragi.snapcast.api;

/**
 * Represents an actual device with the Snapclient software.
 *
 * @author dominic@dmoser.dev
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface Client {

    /**
     * Get the id of this client.
     *
     * @return The id.
     * @since 1.0.0
     */
    String getId();

    /**
     * Get the name of this client.
     *
     * @return The name.
     * @since 1.0.0
     */
    String getName();

    /**
     * Sets the name of the client.
     *
     * @param name The new name of this client.
     * @since 1.0.0
     */
    void setName(String name);

    /**
     * Check if this client is connected to the server.
     *
     * @return True when the client is connected.
     * @since 1.0.0
     */
    boolean isConnected();

    /**
     * Get the latency of the current client.
     *
     * @return The latency in ms.
     * @since 1.0.0
     */
    int getLatency();

    /**
     * Sets the latency of this client.
     *
     * @param latency The new latency of this client.
     * @since 1.0.0
     */
    void setLatency(int latency);

    /**
     * Checks if this client is currently muted.
     *
     * @return True when this client is muted.
     * @since 1.0.0
     */
    boolean isMuted();

    /**
     * Sets if this client is muted or unmuted.
     *
     * @param mute True when this client should be muted.
     * @since 1.0.0
     */
    void setMuted(boolean mute);

    /**
     * Get the volume at which this client is playing music at.
     *
     * @return The current volume as an integer in the range from 0 to 100%.
     * @since 1.0.0
     */
    int getVolume();

    /**
     * Sets the volume of this client. The value can value between 0-100 where 0 is silent and 100 is the maximum
     * volume.
     *
     * @param percentage The new volume.
     * @since 1.0.0
     */
    void setVolume(int percentage);

}
