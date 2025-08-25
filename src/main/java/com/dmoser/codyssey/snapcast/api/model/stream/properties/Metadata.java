package com.dmoser.codyssey.snapcast.api.model.stream.properties;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

public record Metadata(
        @JsonInclude(NON_ABSENT)
        Optional<String> trackId,

        @JsonInclude(NON_ABSENT)
        Optional<String> file,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> duration,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> artist,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> artistSort,

        @JsonInclude(NON_ABSENT)
        Optional<String> album,

        @JsonInclude(NON_ABSENT)
        Optional<String> albumSort,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> albumArtist,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> albumArtistSort,

        @JsonInclude(NON_ABSENT)
        Optional<String> name,

        @JsonInclude(NON_ABSENT)
        Optional<String> date,

        @JsonInclude(NON_ABSENT)
        Optional<String> originalDate,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> composer,

        @JsonInclude(NON_ABSENT)
        Optional<String> performer,

        @JsonInclude(NON_ABSENT)
        Optional<String> conductor,

        @JsonInclude(NON_ABSENT)
        Optional<String> work,

        @JsonInclude(NON_ABSENT)
        Optional<String> grouping,

        @JsonInclude(NON_ABSENT)
        Optional<String> comment,

        @JsonInclude(NON_ABSENT)
        Optional<String> label,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzArtistId,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzAlbumId,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzAlbumArtistId,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzTrackId,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzReleaseTrackId,

        @JsonInclude(NON_ABSENT)
        Optional<String> musicbrainzWorkId,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> lyrics,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> bpm,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> autoRating,

        @JsonInclude(NON_ABSENT)
        Optional<String> contentCreated,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> discNumber,

        @JsonInclude(NON_ABSENT)
        Optional<String> firstUsed,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> genre,

        @JsonInclude(NON_ABSENT)
        Optional<String> lastUsed,

        @JsonInclude(NON_ABSENT)
        Optional<List<String>> lyricist,

        @JsonInclude(NON_ABSENT)
        Optional<String> title,

        @JsonInclude(NON_ABSENT)
        Optional<String> trackNumber,

        @JsonInclude(NON_ABSENT)
        Optional<String> url,

        @JsonInclude(NON_ABSENT)
        Optional<String> artUrl,

        @JsonInclude(NON_ABSENT)
        Optional<ArtData> artData,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> useCount,

        @JsonInclude(NON_ABSENT)
        Optional<Integer> userRating,

        @JsonInclude(NON_ABSENT)
        Optional<String> spotifyArtistId,

        @JsonInclude(NON_ABSENT)
        Optional<String> spotifyTrackId
) {
}
