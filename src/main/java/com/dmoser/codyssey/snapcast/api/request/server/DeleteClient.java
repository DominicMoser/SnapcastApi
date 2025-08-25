package com.dmoser.codyssey.snapcast.api.request.server;

import com.dmoser.codyssey.snapcast.api.model.Server;
import com.dmoser.codyssey.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.snapcast.api.service.State;

public class DeleteClient {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.SERVER_DELETE_CLIENT, params);
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            state.update(result.server);
        }

    }

    public record Result(Server server) {
    }
}
