package com.fxf.adapter.coinbase.services;

import com.fxf.adapter.coinbase.models.responses.ChannelLevel2SocketResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CoinbaseFeedServiceImpl implements CoinbaseFeedService {
    private final Logger LOG = LoggerFactory.getLogger(CoinbaseFeedServiceImpl.class);
    private boolean received = false;

    public void onChannel2Update(ChannelLevel2SocketResponse response) {
        received = true;
        LOG.info("Received update for product " + response.getProductId() + ":  "
                + response.getChanges());
    }

    public boolean receivedUpdate() {
        return received;
    }
}
