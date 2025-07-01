package com.dmoser.codyssey.bragi.snapcast.api;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.service.Communication;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

import java.net.URI;

public class Api {
    private final Server server;
    private final Group group;
    private final Client client;
    private final Stream stream;

    private final State state;
    private final Communication communication;

    public Api(URI uri) {

        state = State.create();

        communication = Communication
                .builder()
                .withState(state)
                .connect(uri);

        server = new Server(communication, state);
        group = new Group(communication, state);
        client = new Client(communication, state);
        stream = new Stream(communication, state);
    }

    public static String getVersion() {
        return Constants.API_VERSION;
    }

    public Server server() {
        return server;
    }

    public Group group() {
        return group;
    }

    public Client client() {
        return client;
    }

    public Stream stream() {
        return stream;
    }

    public void sendRequest(BaseRequest request) {
        communication.sendRequest(request);
    }
}
