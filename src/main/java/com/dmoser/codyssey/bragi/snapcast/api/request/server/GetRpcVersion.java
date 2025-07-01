package com.dmoser.codyssey.bragi.snapcast.api.request.server;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class GetRpcVersion {
    public static class Request extends BaseRequest<Params> {

        public Request() {
            super(RequestMethod.SERVER_GET_RPC_VERSION);
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, boolean mute) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            // TODO Do Something
        }

    }

    public record Result(int major, int minor, int patch) {
    }
}
