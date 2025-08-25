package com.dmoser.codyssey.snapcast.api;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        Api api = new Api(new URI("ws://127.0.0.1:1780/jsonrpc"));

        while (true) {
            System.out.println("loop");
            Thread.sleep(500);
            try {
                api.stream().getAllStreams().stream().map(stream -> stream.id).forEach(System.out::println);
            } catch (Exception e) {
            }
        }
    }
}
