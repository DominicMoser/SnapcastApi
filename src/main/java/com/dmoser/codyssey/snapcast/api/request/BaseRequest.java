package com.dmoser.codyssey.snapcast.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseRequest<ParamType extends RequestParams> {

    private static int idGen = 0;

    @JsonProperty
    public final int id;
    @JsonProperty
    public final String jsonrpc;
    @JsonProperty
    public final RequestMethod method;
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final ParamType params;

    protected BaseRequest(RequestMethod method, ParamType params) {
        this.id = idGen++;
        this.jsonrpc = "2.0";
        this.method = method;
        this.params = params;
    }

    protected BaseRequest(RequestMethod method) {
        this(method, null);
    }

    public abstract Class<? extends BaseResponse<?>> expectedResponse();

}
