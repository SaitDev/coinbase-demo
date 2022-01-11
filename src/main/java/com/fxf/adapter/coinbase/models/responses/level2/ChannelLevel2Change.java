package com.fxf.adapter.coinbase.models.responses.level2;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fxf.adapter.coinbase.common.json.ChannelLevel2ChangeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonDeserialize(using = ChannelLevel2ChangeDeserializer.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelLevel2Change {
    private String type;
    private Double price;
    private Double amount;
}
