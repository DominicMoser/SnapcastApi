package com.dmoser.codyssey.bragi.snapcast.api.request.server;

import com.dmoser.codyssey.bragi.snapcast.api.model.Server;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class GetStatus {

    public static Request request() {
        return new Request();
    }

    public static class Request extends BaseRequest<Params> {

        public Request() {
            super(RequestMethod.SERVER_GET_STATUS);
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params() implements RequestParams {
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
