package com.fxf.adapter.coinbase.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "fxf.coinbase")
@Data
public class CoinbaseConfiguration {
    private String wsEndpoint;
    private String restEndpoint;

    private List<String> subscribeProducts;
}
