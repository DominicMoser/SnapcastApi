package com.dmoser.codyssey.bragi.snapcast.api.model;

import com.dmoser.codyssey.bragi.snapcast.api.model.stream.Properties;
import com.dmoser.codyssey.bragi.snapcast.api.model.stream.Status;
import com.dmoser.codyssey.bragi.snapcast.api.model.stream.UriSchema;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

public class Stream {
    public String id;
    public Status status;
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Optional<Properties> properties;
    public UriSchema uri;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Stream stream) {
            return stream.id.equals(id);
        }
        return false;
    }
}
