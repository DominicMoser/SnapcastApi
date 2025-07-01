package com.dmoser.codyssey.bragi.snapcast.api.request;

import com.dmoser.codyssey.bragi.snapcast.api.service.State;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseResponse<ResultType> implements Response {

    @JsonProperty
    protected String id;
    @JsonProperty
    protected String jsonrpc;
    @JsonProperty
    protected ResultType result;
    protected RequestParams requestParams = null;

    protected BaseResponse() {
    }

    public String id() {
        return id;
    }

    public String jsonrpc() {
        return jsonrpc;
    }

    public ResultType result() {
        return result;
    }

    @Override
    public void addRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    public void process(State state) {
        // When requestParams is null this will be skipped.
        updateState(state, result);
    }

    protected abstract void updateState(State state, ResultType result);


}
