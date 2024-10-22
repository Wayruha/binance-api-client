package com.binance.api.client.domain;

public enum PositionSide {
    BOTH,
    SHORT,
    LONG;

    public PositionSide opposite() {
        switch (this) {
            case SHORT:
                return LONG;
            case LONG:
                return SHORT;
            default:
                return BOTH;
        }
    }
}
