package com.dmoser.codyssey.bragi.snapcast.api.request;

import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public interface processable {
    void process(State state);
}
