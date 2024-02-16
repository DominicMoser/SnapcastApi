package com.dmoser.codyssey.snapcast.api.dto;

public enum StreamRate {
    HZ_44100("44100");

    final String rate;

    StreamRate(String rate){
        this.rate = rate;
    }

    public String getRate() {
        return this.rate;
    }
}
