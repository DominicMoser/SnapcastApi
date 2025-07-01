package com.dmoser.codyssey.bragi.snapcast.api.model.stream;

public record UriSchema(
        String fragment,
        String host,
        String path,
        QuerySchema query,
        String raw,
        String scheme
) {
}
