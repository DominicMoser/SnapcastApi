package com.dmoser.codyssey.bragi.snapcast.api.request.group;

import com.dmoser.codyssey.bragi.snapcast.api.model.Group;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class GetStatus {

    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.GROUP_GET_STATUS, params);
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
            state.update(result.group);
        }

    }

    public record Result(Group group) {
    }

}
