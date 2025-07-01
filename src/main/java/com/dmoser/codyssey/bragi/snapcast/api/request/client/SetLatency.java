package com.dmoser.codyssey.bragi.snapcast.api.request.client;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class SetLatency {

    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.CLIENT_SET_LATENCY, params);
        }

        public Request(String id, int latency) {
            this(new Params(id, latency));
        }

        @Override
        public Class<? extends BaseResponse<?>> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, int latency) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {


        @Override
        protected void updateState(State state, Result result) {
            if (requestParams instanceof Request latencyRequest) {
                state.getServer()
                        .groups
                        .stream()
                        .flatMap(group -> group.clients.stream())
                        .filter(client -> client.id.equals(latencyRequest.params.id))
                        .forEach(client -> client.config.latency = result.latency);
            }
        }
    }

    public record Result(int latency) {
    }

}
