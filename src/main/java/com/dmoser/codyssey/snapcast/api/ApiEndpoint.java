package com.dmoser.codyssey.snapcast.api;

import com.dmoser.codyssey.snapcast.api.service.Communication;
import com.dmoser.codyssey.snapcast.api.service.State;

public abstract class ApiEndpoint {

    protected final Communication communication;
    protected final State state;

    public ApiEndpoint(Communication communication, State state) {
        this.communication = communication;
        this.state = state;
    }
}
