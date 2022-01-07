package com.fxf.adapter.coinbase.models.responses;

import lombok.Data;

@Data
public class BaseSocketResponse {
    private String type;
    private String message;
}
