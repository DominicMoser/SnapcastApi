package com.dmoser.codyssey.bragi.snapcast.api.model.stream;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

public record QuerySchema(
        String chunk_ms,
        String codec,
        String name,
        String sampleformat,
        @JsonInclude(JsonInclude.Include.NON_ABSENT)
        Optional<String> mode
) {
}
