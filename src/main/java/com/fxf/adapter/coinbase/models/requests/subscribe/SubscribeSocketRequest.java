package com.fxf.adapter.coinbase.models.requests.subscribe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fxf.adapter.coinbase.common.Constants;
import com.fxf.adapter.coinbase.models.requests.BaseSocketRequest;
import lombok.Data;

import java.util.List;

@Data
public class SubscribeSocketRequest extends BaseSocketRequest {
    @JsonProperty("product_ids")
    private List<String> productIds;
    /**
     * can be string or com.fxf.adapter.coinbase.models.requests.subscribe.ChannelRequest
     */
    private List channels;

    @Override
    public String getType() {
        return Constants.SOCKET_REQUEST_SUBSCRIBE;
    }
}
