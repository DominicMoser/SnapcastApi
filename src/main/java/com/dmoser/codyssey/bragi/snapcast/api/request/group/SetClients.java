package com.dmoser.codyssey.bragi.snapcast.api.request.group;

import com.dmoser.codyssey.bragi.snapcast.api.model.Server;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.bragi.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.bragi.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.bragi.snapcast.api.service.State;

import java.util.Set;

public class SetClients {

    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.GROUP_SET_CLIENTS, params);
        }

        public Request(String groupId, Set<String> clientIds) {
            this(new Params(groupId, clientIds));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, Set<String> clients) implements RequestParams {
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
