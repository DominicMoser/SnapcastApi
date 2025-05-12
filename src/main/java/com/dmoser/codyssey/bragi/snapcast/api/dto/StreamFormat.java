package com.dmoser.codyssey.bragi.snapcast.api.dto;

public enum StreamFormat {
    S16LE("16");

    final String format;

    StreamFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }
}
