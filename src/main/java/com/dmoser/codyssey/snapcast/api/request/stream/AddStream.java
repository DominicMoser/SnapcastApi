package com.dmoser.codyssey.snapcast.api.request.stream;

import com.dmoser.codyssey.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.snapcast.api.service.State;

public class AddStream {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.STREAM_ADD, params);
        }

        public Request(String streamUri) {
            this(new Params(streamUri));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String streamUri) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {


        @Override
        protected void updateState(State state, Result result) {
            state.update();
        }

    }

    public record Result(String stream_id) {
    }
}
