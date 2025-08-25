package com.dmoser.codyssey.snapcast.api.request.client;

import com.dmoser.codyssey.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.snapcast.api.service.State;

public class SetName {

    public static class Request extends BaseRequest<Params> {
        public Request(Params params) {
            super(RequestMethod.CLIENT_SET_NAME, params);
        }

        public Request(String id, String name) {
            this(new Params(id, name));
        }

        @Override
        public Class<? extends BaseResponse<?>> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, String name) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            if (requestParams instanceof Params(String id, String name)) {
                state.getServer()
                        .groups
                        .stream()
                        .flatMap(group -> group.clients.stream())
                        .filter(client -> client.id.equals(id))
                        .forEach(client -> client.config.name = result.name);
            }
        }
    }

    public record Result(String name) {
    }
}
