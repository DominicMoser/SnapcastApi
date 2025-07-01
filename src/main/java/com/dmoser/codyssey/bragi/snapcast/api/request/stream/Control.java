package com.dmoser.codyssey.bragi.snapcast.api.request.stream;

import com.dmoser.codyssey.bragi.snapcast.api.model.control.Command;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class Control {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.STREAM_CONTROL, params);
        }

        public Request(String streamId, Command command) {
            this(new Params(streamId, command));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, Command command) implements RequestParams {
    }

    public static class Response extends BaseResponse<String> {

        @Override
        protected void updateState(State state, String result) {
            state.update();
        }

    }

}
