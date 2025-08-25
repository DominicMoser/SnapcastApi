package com.dmoser.codyssey.snapcast.api.request;

import com.dmoser.codyssey.snapcast.api.service.State;

public interface processable {
    void process(State state);
}
