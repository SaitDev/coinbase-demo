package com.fxf.adapter.coinbase.common;

public abstract class Constants {
    public static final int KB = 1024;
    public static final int MB = KB * 1024;

    public static final String SOCKET_REQUEST_SUBSCRIBE = "subscribe";

    public static final String CHANNEL_LEVEL_2 = "level2";
    public static final String CHANNEL_HEARTBEAT = "heartbeat";

    public static final String RESPONSE_CHANNEL_LEVEL_2 = "l2update";

    public static final String PRODUCT_ETHEREUM_BITCOIN = "ETH-BTC";
    public static final String PRODUCT_ETHEREUM_USD = "ETH-USD";

    private Constants() {}
}
