package com.fxf.adapter.coinbase.services;

import com.fxf.adapter.coinbase.models.responses.level2.ChannelLevel2SocketResponse;

public interface CoinbaseFeedService {
    void onChannel2Update(ChannelLevel2SocketResponse response);

    boolean receivedUpdate();
}
