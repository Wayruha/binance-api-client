package com.binance.api.client.domain.account.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
public class MarginOrderStatusRequest extends OrderStatusRequest {
    private final boolean isIsolated;

    public MarginOrderStatusRequest(String symbol, Long orderId, boolean isIsolated) {
        super(symbol, orderId);
        this.isIsolated = isIsolated;
    }

    public MarginOrderStatusRequest(String symbol, String origClientOrderId, boolean isIsolated) {
        super(symbol, origClientOrderId);
        this.isIsolated = isIsolated;
    }
}
