package com.dmoser.codyssey.snapcast.api;

import com.dmoser.codyssey.snapcast.api.dto.StreamFormat;
import com.dmoser.codyssey.snapcast.api.dto.StreamRate;
import com.dmoser.codyssey.snapcast.api.dto.StreamTCPMode;

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

    /**
     * Interface for creating new Streams.
     *
     * @since 2.0.0
     */
    interface StreamBuilder {

        /**
         * Creates the builder for new {@link Stream} using the TCP protocol.
         *
         * @return The tcp stream builder.
         * @since 2.0.0
         */
        static StreamBuilderTcp withTcp() {
            return new StreamBuilderTcp();
        }

        static boolean checkMsg(String msg) {
            return true;
        }

        /**
         * Used to create {@link Stream}'s using the TCP protocol.
         *
         * @since 2.0.0
         */
        class StreamBuilderTcp implements StreamBuilder {

            /**
             * Creates the StreamConstruction message.
             *
             * @param host     The origin of the stream. Without the port number and protocol. I.e. 127.0.0.1 or
             *                 localhost or SOMESERVER.
             * @param port     The port number of the origin.
             * @param name     The name this stream should have.
             * @param mode     The mode in which the stream runs.
             * @param channels The number of channels of this stream.
             * @param rate     The rate in which the audio is formatted.
             * @param format   The format of the stream.
             * @return The message used in {@link Server#createNewStream(StreamConstructionMsg)} for creating new
             * Streams.
             * @since 2.0.0
             */
            public StreamConstructionMsg create(String host, int port, String name, StreamTCPMode mode, int channels, StreamRate rate, StreamFormat format) {
                String template = "tcp://%s:%d?name=%s&mode=%s&sampleformat=%s:%s:%d";
                String msg = template.formatted(
                        host,
                        port,
                        name,
                        mode.getSnapserverMode(),
                        rate.getRate(),
                        format.getFormat(),
                        channels);
                return new StreamConstructionMsg(msg);
            }

        }

        /**
         * The construction message used in {@link Server#createNewStream(StreamConstructionMsg)}.
         *
         * @since 2.0.0
         */
        class StreamConstructionMsg {

            String msg;

            protected StreamConstructionMsg(String msg) {

            }

            public String getMsg() {
                return msg;
            }

        }

    }

}
