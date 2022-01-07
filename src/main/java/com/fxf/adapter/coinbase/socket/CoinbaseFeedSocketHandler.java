package com.fxf.adapter.coinbase.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxf.adapter.coinbase.common.CoinbaseConfiguration;
import com.fxf.adapter.coinbase.common.Constants;
import com.fxf.adapter.coinbase.models.requests.subscribe.SubscribeSocketRequest;
import com.fxf.adapter.coinbase.services.CoinbaseFeedService;
import com.fxf.adapter.core.BaseWebsocketAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.Arrays;

@Component
@ClientEndpoint
public class CoinbaseFeedSocketHandler extends BaseWebsocketAdapter {
    //region Beans
    private final CoinbaseConfiguration coinbaseConfiguration;
    private final ObjectMapper objectMapper;
    private final CoinbaseFeedService coinbaseFeedService;
    //endregion

    private CoinbaseFeedMessageHandler coinbaseFeedMessageHandler;


    public CoinbaseFeedSocketHandler(
            CoinbaseConfiguration coinbaseConfiguration,
            ObjectMapper objectMapper,
            CoinbaseFeedService coinbaseFeedService
    ) {
        this.coinbaseConfiguration = coinbaseConfiguration;
        this.objectMapper = objectMapper;
        this.coinbaseFeedService = coinbaseFeedService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connect() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxBinaryMessageBufferSize(Constants.MB * 2);
        container.setDefaultMaxTextMessageBufferSize(Constants.MB * 2);
        container.connectToServer(this, new URI(coinbaseConfiguration.getWsEndpoint()));
    }

    protected void doOpen(Session userSession) {
        subscribe();
    }

    protected void doClose(Session userSession, CloseReason reason) {
        this.coinbaseFeedMessageHandler = null;
    }

    @Override
    protected MessageHandler createMessageHandler() {
        coinbaseFeedMessageHandler = new CoinbaseFeedMessageHandler(objectMapper, userSession, coinbaseFeedService);
        return coinbaseFeedMessageHandler;
    }

    private void subscribe() {
        SubscribeSocketRequest request = new SubscribeSocketRequest();
        request.setProductIds(coinbaseConfiguration.getSubscribeProducts());
        request.setChannels(Arrays.asList(
                Constants.CHANNEL_LEVEL_2
        ));

        coinbaseFeedMessageHandler.sendMessage(request);
    }
}
