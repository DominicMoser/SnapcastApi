package com.dmoser.codyssey.snapcast.api.service;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class UtilityService {

    private static UtilityService instance;
    private final com.fasterxml.jackson.databind.ObjectMapper mapper;

    private UtilityService() {
        this.mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        this.mapper.registerModule(new Jdk8Module());
    }

    public static com.fasterxml.jackson.databind.ObjectMapper objectMapper() {
        if (instance == null) {
            instance = new UtilityService();
        }
        return instance.mapper;
    }

}
