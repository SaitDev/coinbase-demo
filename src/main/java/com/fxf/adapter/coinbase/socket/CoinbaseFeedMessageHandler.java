package com.fxf.adapter.coinbase.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxf.adapter.coinbase.common.Constants;
import com.fxf.adapter.coinbase.models.requests.BaseSocketRequest;
import com.fxf.adapter.coinbase.models.responses.BaseSocketResponse;
import com.fxf.adapter.coinbase.models.responses.level2.ChannelLevel2SocketResponse;
import com.fxf.adapter.coinbase.services.CoinbaseFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.MessageHandler;
import javax.websocket.Session;

public class CoinbaseFeedMessageHandler implements MessageHandler.Whole<String> {
    private final Logger LOG = LoggerFactory.getLogger(CoinbaseFeedMessageHandler.class);
    private ObjectMapper objectMapper;
    private Session userSession;
    private CoinbaseFeedService coinbaseFeedService;

    public CoinbaseFeedMessageHandler(
            ObjectMapper objectMapper,
            Session userSession,
            CoinbaseFeedService coinbaseFeedService
    ) {
        this.objectMapper = objectMapper;
        this.userSession = userSession;
        this.coinbaseFeedService = coinbaseFeedService;
    }

    @Override
    public void onMessage(String message) {
        try {
            BaseSocketResponse response = objectMapper.readValue(message, BaseSocketResponse.class);
            if (Constants.RESPONSE_CHANNEL_LEVEL_2.equals(response.getType())) {
                response = objectMapper.readValue(message, ChannelLevel2SocketResponse.class);
                coinbaseFeedService.onChannel2Update((ChannelLevel2SocketResponse) response);
            } else {
                LOG.info(message);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends BaseSocketRequest> void sendMessage(T request) {
        try {
            userSession.getAsyncRemote().sendText(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
