package com.dmoser.codyssey.snapcast.api.request.client;

import com.dmoser.codyssey.snapcast.api.model.group.client.Volume;
import com.dmoser.codyssey.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.snapcast.api.service.State;

public class SetVolume {

    public static class Request extends BaseRequest<Params> {
        public Request(Params params) {
            super(RequestMethod.CLIENT_SET_VOLUME, params);
        }

        public Request(String id, boolean setMuted, int percent) {
            this(new Params(id, new Volume(setMuted, percent)));
        }

        @Override
        public Class<? extends BaseResponse<?>> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, Volume volume) implements RequestParams {
    }

    public static class Response extends BaseResponse<Result> {

        @Override
        protected void updateState(State state, Result result) {
            if (requestParams instanceof Params(String id, Volume volume)) {
                state.getServer()
                        .groups
                        .stream()
                        .flatMap(group -> group.clients.stream())
                        .filter(client -> client.id.equals(id))
                        .forEach(client -> client.config.volume = result.volume);
            }
        }
    }

    public record Result(Volume volume) {
    }
}
