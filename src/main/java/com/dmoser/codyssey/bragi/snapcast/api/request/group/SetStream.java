package com.dmoser.codyssey.bragi.snapcast.api.request.group;

import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

public class SetStream {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.GROUP_SET_STREAM, params);
        }

        public Request(String groupId, String streamId) {
            this(new Params(groupId, streamId));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, String stream_id) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            if (requestParams instanceof Params params) {
                state.getServer()
                        .groups
                        .stream()
                        .filter(group -> group.id.equals(params.id()))
                        .forEach(group -> group.stream_id = result.stream_id());
            }
        }
    }


    public record Result(String stream_id) {
    }
}
