package com.fxf.adapter.core;

import com.fxf.adapter.coinbase.socket.CoinbaseFeedSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.CloseReason;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public abstract class BaseWebsocketAdapter {
    protected final Logger LOG = LoggerFactory.getLogger(CoinbaseFeedSocketHandler.class);
    protected Session userSession;
    private MessageHandler messageHandler;

    @OnOpen
    public void onOpen(Session userSession) {
        LOG.info("opened websocket");

        this.userSession = userSession;
        userSession.addMessageHandler(createMessageHandler());

        doOpen(userSession);
    }

    protected abstract void doOpen(Session userSession);

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        LOG.info("closing websocket... {}", reason);

        this.userSession = null;
        doClose(userSession, reason);
    }

    protected abstract void doClose(Session userSession, CloseReason reason);

    @OnError
    public void onError(Throwable error) {
        LOG.error("websocket error", error);
    }

    protected abstract MessageHandler createMessageHandler();
}
