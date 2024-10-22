package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Currently it only supports transfers to Futures account.
 * May be extended to also support margin transfers.
 */
public enum SpotTransferType {
    MAIN_UMFUTURE(1),
    UMFUTURE_MAIN(2),
    MAIN_CMFUTURE(3),
    CMFUTURE_MAIN(4);

    private final int value;

    @JsonValue
    public int getValue() {
        return value;
    }

    SpotTransferType(int value) {
        this.value = value;
    }
}
