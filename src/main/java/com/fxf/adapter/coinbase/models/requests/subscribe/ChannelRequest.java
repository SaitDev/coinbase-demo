package com.fxf.adapter.coinbase.models.requests.subscribe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChannelRequest {
    private String name;
    @JsonProperty("product_ids")
    private List<String> productIds;
}
