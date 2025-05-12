package com.dmoser.codyssey.bragi.snapcast.api;

import java.net.URL;

/**
 * A builder interface for creating Server instances.
 */
public interface ServerBuilder {

    /**
     * The method which will create a new server.
     *
     * @param url The url of the server.
     * @return The new server.
     */
    Server connect(URL url);


}
