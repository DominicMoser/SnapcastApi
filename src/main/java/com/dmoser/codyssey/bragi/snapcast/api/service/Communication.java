package com.dmoser.codyssey.bragi.snapcast.api.service;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.processable;
import com.dmoser.codyssey.bragi.snapcast.api.request.server.GetStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Optional;

public class Communication extends org.java_websocket.client.WebSocketClient {

    static final Object reconnectSemaphore = new Object();
    static final Object semaphore = new Object();
    private final State state;
    boolean connected = false;

    private Communication(URI uri, State state) {
        super(uri);
        this.state = state;
        state.setCommunication(this);
        new Thread(() -> {
            while (true) {
                System.out.println("Connecting");
                while (this.isOpen()) {
                    System.out.println("Open");
                    synchronized (reconnectSemaphore) {
                        try {
                            reconnectSemaphore.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                try {
                    System.out.println("ConnectBlocking");
                    this.reconnectBlocking();
                    if (this.isOpen()) {
                        System.out.println("Open");
                        this.connected = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        connect();
    }

    public static CommunicationBuilder builder() {
        return new CommunicationBuilder();
    }

    public void waitForConnection() {
        while (!connected) {
            synchronized (semaphore) {
                try {
                    semaphore.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendRequest(BaseRequest<?> request) {
        String requestString = ParsingService.parseRequestParams(request);
        send(requestString);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        synchronized (semaphore) {
            connected = true;
            semaphore.notifyAll();
            sendRequest(new GetStatus.Request());
        }

    }

    @Override
    public void onMessage(String msg) {
        try {
            processable message = ParsingService.parseMsg(msg);
            message.process(state);
        } catch (JsonProcessingException e) {
            System.out.println(msg);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        synchronized (reconnectSemaphore) {
            connected = false;
            reconnectSemaphore.notifyAll();
        }
    }

    @Override
    public void onError(Exception ex) {
        synchronized (reconnectSemaphore) {
            connected = false;
            reconnectSemaphore.notifyAll();
        }
    }

    public State getState() {
        return state;
    }

    public static class CommunicationBuilder {

        private Optional<State> state = Optional.empty();

        public Communication connect(URI uri) {
            return new Communication(
                    uri,
                    state.orElse(State.create())
            );
        }

        public CommunicationBuilder withState(State state) {
            this.state = Optional.of(state);
            return this;
        }


    }

}
