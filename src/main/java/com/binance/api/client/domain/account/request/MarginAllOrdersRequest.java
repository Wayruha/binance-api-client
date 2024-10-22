package com.binance.api.client.domain.account.request;

public class MarginAllOrdersRequest extends  AllOrdersRequest {
    private boolean isIsolated;
    public MarginAllOrdersRequest(String symbol) {
        super(symbol);
    }

    public boolean isIsolated() {
        return isIsolated;
    }

    public MarginAllOrdersRequest isIsolated(boolean isIsolated) {
        this.isIsolated = isIsolated;
        return this;
    }
}
