package com.dmoser.codyssey.bragi.snapcast.api;

import com.dmoser.codyssey.bragi.snapcast.api.request.group.SetName;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {

        Api api = new Api(new URI("ws://10.0.0.56:1780/jsonrpc"));

        api.sendRequest(new SetName.Request(new SetName.Params("e9c4f5a4-6f23-c020-0b0b-43b7fc817c35", "test")));

        while (true) {
            Thread.sleep(500);
            api.stream().getAllStreams().stream().map(stream -> stream.id).forEach(System.out::println);
        }
    }
}
