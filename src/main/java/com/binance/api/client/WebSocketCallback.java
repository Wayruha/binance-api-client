package com.binance.api.client;

import okhttp3.WebSocket;

public interface WebSocketCallback<T> extends BinanceApiCallback<T> {
    /**
     * Called if ws connected
     */
    default void onConnected(final WebSocket ws) {
    }

    /**
     * Called if ws closed in any cases: gracefully shutdown or exception
     */
    default void onClosed(final WebSocket ws) {
    }
}
