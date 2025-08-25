package com.dmoser.codyssey.snapcast.api.model.stream;

import com.dmoser.codyssey.snapcast.api.model.stream.properties.LoopStatus;
import com.dmoser.codyssey.snapcast.api.model.stream.properties.Metadata;
import com.dmoser.codyssey.snapcast.api.model.stream.properties.PlaybackStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

public record Properties(
        @JsonInclude(NON_ABSENT)
        Optional<PlaybackStatus> playbackStatus,

        @JsonInclude(NON_ABSENT)
        Optional<LoopStatus> loopStatus,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> shuffle,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> volume,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> mute,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> rate,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> position,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canGoNext,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canGoPrevious,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canPlay,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canPause,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canSeek,

        @JsonInclude(NON_ABSENT)
        Optional<Boolean> canControl,

        @JsonInclude(NON_ABSENT)
        Optional<Metadata> metadata
) {
}
