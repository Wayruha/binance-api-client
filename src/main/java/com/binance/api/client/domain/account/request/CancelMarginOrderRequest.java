package com.binance.api.client.domain.account.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class CancelMarginOrderRequest extends CancelOrderRequest {
    boolean isIsolated;

    public CancelMarginOrderRequest(String symbol, Long orderId, boolean isIsolated) {
        super(symbol, orderId);
        this.isIsolated = isIsolated;
    }

    public CancelMarginOrderRequest(String symbol, String origClientOrderId, boolean isIsolated) {
        super(symbol, origClientOrderId);
        this.isIsolated = isIsolated;
    }
}
