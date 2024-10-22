package com.binance.api.client.domain.account.request;

import com.binance.api.client.domain.MarginChangeType;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.math.BigDecimal;

public class ChangeMarginResponse {
    @JsonUnwrapped
    private StatusResponse response;
    private BigDecimal amount;
    private MarginChangeType type;
}
