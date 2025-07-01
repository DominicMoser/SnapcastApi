package com.dmoser.codyssey.bragi.snapcast.api.request.stream;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class RemoveStream {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.STREAM_REMOVE, params);
        }

        public Request(String streamId) {
            this(new Params(streamId));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String streamId) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {


        @Override
        protected void updateState(State state, Result result) {
            state.update();
            // TODO do something Remove Client
        }

    }

    public record Result(String stream_id) {
    }
}
