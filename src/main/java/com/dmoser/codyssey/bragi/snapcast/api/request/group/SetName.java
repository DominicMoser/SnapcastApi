package com.dmoser.codyssey.bragi.snapcast.api.request.group;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class SetName {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.GROUP_SET_NAME, params);
        }

        public Request(String id, String newName) {
            this(new Params(id, newName));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, String name) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            if (requestParams instanceof Params params) {
                state.getServer()
                        .groups
                        .stream()
                        .filter(group -> group.id.equals(params.id()))
                        .forEach(group -> group.name = result.name);
            }
        }

    }

    public record Result(String name) {
    }
}
