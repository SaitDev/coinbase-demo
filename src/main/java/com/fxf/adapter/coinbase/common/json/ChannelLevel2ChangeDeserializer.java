package com.fxf.adapter.coinbase.common.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fxf.adapter.coinbase.models.responses.level2.ChannelLevel2Change;

import java.io.IOException;

public class ChannelLevel2ChangeDeserializer extends StdDeserializer<ChannelLevel2Change> {

    protected ChannelLevel2ChangeDeserializer() {
        super(ChannelLevel2Change.class);
    }

    @Override
    public ChannelLevel2Change deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        return new ChannelLevel2Change(
                node.get(0).asText(),
                node.get(1).asDouble(),
                node.get(2).asDouble()
        );
    }
}
