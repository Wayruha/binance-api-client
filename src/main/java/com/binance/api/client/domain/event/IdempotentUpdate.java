package com.binance.api.client.domain.event;

public interface IdempotentUpdate {
    boolean isProcessed();

    void setProcessed(boolean processed);
}
