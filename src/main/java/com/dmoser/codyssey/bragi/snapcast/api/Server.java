package com.dmoser.codyssey.bragi.snapcast.api;

import com.dmoser.codyssey.bragi.snapcast.api.service.Communication;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class Server extends ApiEndpoint {
    public Server(Communication communication, State state) {
        super(communication, state);
    }


}
