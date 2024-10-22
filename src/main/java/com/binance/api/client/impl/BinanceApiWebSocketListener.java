package com.binance.api.client.impl;

import com.binance.api.client.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Binance API WebSocket listener.
 */
public class BinanceApiWebSocketListener<T> extends WebSocketListener {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final WebSocketCallback<T> callback;
    private final ObjectReader objectReader;
    private boolean isOpened = false;

    public BinanceApiWebSocketListener(final WebSocketCallback<T> callback, final Class<T> eventClass) {
        this.callback = callback;
        this.objectReader = mapper.readerFor(eventClass);
    }

    public BinanceApiWebSocketListener(final WebSocketCallback<T> callback, final TypeReference<T> eventTypeReference) {
        this.callback = callback;
        this.objectReader = mapper.readerFor(eventTypeReference);
    }

    @Override
    public void onOpen(@NotNull final WebSocket webSocket, @NotNull final Response response) {
        isOpened = true;
        callback.onConnected(webSocket);
    }

    @Override
    public void onMessage(@NotNull final WebSocket webSocket, @NotNull final String text) {
        try {
            GlobalConfig.webSocketLogger.trace(text);
            T event = objectReader.readValue(text);
            callback.onResponse(event);
        } catch (RuntimeException | IOException e) {
            GlobalConfig.webSocketLogger.error("ERROR WebSocket: " + e.getMessage());
            callback.onError(e);
        }
    }

    //Called when websocket started closing gracefully (received close frame from peer)
    @Override
    public void onClosing(@NotNull final WebSocket webSocket, final int code, @NotNull final String reason) {
        webSocket.close(code, reason);
        GlobalConfig.webSocketLogger.info(String.format("Closing WebSocket: code=%s, reason=%s", code, reason));
    }

    //Called when websocket closed gracefully
    @Override
    public void onClosed(@NotNull final WebSocket webSocket, int code, @NotNull String reason) {
        callback.onClosed(webSocket);
        GlobalConfig.webSocketLogger.info(String.format("Closed WebSocket: code=%s, reason=%s", code, reason));
    }

    //Called when ws closed with error or error on init ws
    @Override
    public void onFailure(@NotNull final WebSocket webSocket, @NotNull final Throwable t, final Response response) {
        GlobalConfig.webSocketLogger.error(String.format("Failed WebSocket: t=%s, response=%s", t.getMessage(), response));
        callback.onFailure(t);
        if (isOpened) {
            callback.onClosed(webSocket);
        }
    }
}
