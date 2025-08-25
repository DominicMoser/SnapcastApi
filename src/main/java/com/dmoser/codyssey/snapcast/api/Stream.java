package com.dmoser.codyssey.snapcast.api;

import com.dmoser.codyssey.snapcast.api.model.control.Command;
import com.dmoser.codyssey.snapcast.api.model.setProperty.PropertyType;
import com.dmoser.codyssey.snapcast.api.request.stream.AddStream;
import com.dmoser.codyssey.snapcast.api.request.stream.Control;
import com.dmoser.codyssey.snapcast.api.request.stream.RemoveStream;
import com.dmoser.codyssey.snapcast.api.request.stream.SetProperty;
import com.dmoser.codyssey.snapcast.api.service.Communication;
import com.dmoser.codyssey.snapcast.api.service.State;

import java.util.List;

public class Stream extends ApiEndpoint {

    public Stream(Communication communication, State state) {
        super(communication, state);
    }

    public void setProperty(String streamId, PropertyType propertyType, Object value) {
        communication.sendRequest(new SetProperty.Request(streamId, propertyType, value));
    }

    public void addStream(String streamUri) {
        communication.sendRequest(new AddStream.Request(streamUri));
    }

    public void removeStream(String streamId) {
        communication.sendRequest(new RemoveStream.Request(streamId));
    }


    public void control(String streamId, Command command) {
        communication.sendRequest(new Control.Request(streamId, command));
    }

    public com.dmoser.codyssey.snapcast.api.model.Stream get(String streamId) {
        return state.getStreams()
                .stream()
                .filter(stream -> stream.id.equals(streamId))
                .findFirst()
                .get();
    }

    public List<com.dmoser.codyssey.snapcast.api.model.Stream> getAllStreams() {
        return state.getStreams();
    }
}
