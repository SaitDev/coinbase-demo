package com.fxf.adapter.coinbase.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChannelLevel2SocketResponse extends BaseSocketResponse {
    @JsonProperty("product_id")
    private String productId;
    private List<List<String>> changes;
}
