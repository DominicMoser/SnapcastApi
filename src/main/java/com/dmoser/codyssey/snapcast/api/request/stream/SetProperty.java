package com.dmoser.codyssey.snapcast.api.request.stream;

import com.dmoser.codyssey.snapcast.api.model.setProperty.PropertyType;
import com.dmoser.codyssey.snapcast.api.request.BaseRequest;
import com.dmoser.codyssey.snapcast.api.request.BaseResponse;
import com.dmoser.codyssey.snapcast.api.request.RequestMethod;
import com.dmoser.codyssey.snapcast.api.request.RequestParams;
import com.dmoser.codyssey.snapcast.api.service.State;

public class SetProperty {
    public static class Request extends BaseRequest<Params> {

        public Request(Params params) {
            super(RequestMethod.STREAM_SET_PROPERTY, params);
        }

        public Request(String streamId, PropertyType propertyType, Object value) {
            this(new Params(streamId, propertyType, value));
        }

        @Override
        public Class<? extends Response> expectedResponse() {
            return Response.class;
        }
    }

    public record Params(String id, PropertyType property, Object value) implements RequestParams {
    }

    public static class Response extends BaseResponse<String> {

        @Override
        protected void updateState(State state, String result) {
            state.update();
        }

    }
}
