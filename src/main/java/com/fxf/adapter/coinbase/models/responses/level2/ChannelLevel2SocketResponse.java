package com.fxf.adapter.coinbase.models.responses.level2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fxf.adapter.coinbase.models.responses.BaseSocketResponse;
import lombok.Data;

import java.util.List;

@Data
public class ChannelLevel2SocketResponse extends BaseSocketResponse {
    @JsonProperty("product_id")
    private String productId;
    private List<ChannelLevel2Change> changes;
}
