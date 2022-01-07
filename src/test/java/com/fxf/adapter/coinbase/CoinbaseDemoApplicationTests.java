package com.fxf.adapter.coinbase;

import com.fxf.adapter.coinbase.services.CoinbaseFeedService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CoinbaseDemoApplicationTests {
    @Autowired
    private CoinbaseFeedService coinbaseFeedService;

    @Test
    void contextLoads() throws Exception {
        //wait for few update from coinbase socket
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(5, TimeUnit.SECONDS);

        Assertions.assertEquals(true, coinbaseFeedService.receivedUpdate());
    }

}
