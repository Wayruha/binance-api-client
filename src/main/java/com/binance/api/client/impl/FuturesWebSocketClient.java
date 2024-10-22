package com.binance.api.client.impl;

import com.binance.api.client.*;
import com.binance.api.client.domain.event.*;
import com.binance.api.client.domain.market.CandlestickInterval;
import okhttp3.*;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

import static com.binance.api.client.config.BinanceApiConfig.*;

/**
 * Binance API WebSocket client implementation using OkHttp.
 */
public class FuturesWebSocketClient implements WebSocketClient {
    protected final OkHttpClient client;
    protected final boolean isTestnet;

    public FuturesWebSocketClient(final OkHttpClient client, final boolean isTestnet) {
        this.client = client;
        this.isTestnet = isTestnet;
    }

    protected WebSocket createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
        final String url = isTestnet ? getFuturesStreamTestnetUrl() : getFuturesStreamUrl();
        String streamingUrl = String.format("%s/%s", url, channel);
        Request request = new Request.Builder().url(streamingUrl).build();
        return client.newWebSocket(request, listener);
    }

    @Override
    public WebSocket onUserDataUpdateEvent(String listenKey, WebSocketCallback<UserDataUpdateEvent> callback) {
        return createNewWebSocket(listenKey, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
    }

    @Override
    public WebSocket onDepthEvent(String symbols, WebSocketCallback<DepthEvent> callback) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public WebSocket onCandlestickEvent(String symbols, CandlestickInterval interval, WebSocketCallback<CandlestickEvent> callback) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public WebSocket onAggTradeEvent(final String symbols, final WebSocketCallback<AggTradeEvent> callback) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public WebSocket onTickerEvent(String symbols, WebSocketCallback<TickerEvent> callback) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public WebSocket onAllMarketTickersEvent(WebSocketCallback<List<TickerEvent>> callback) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public WebSocket onBookTickerEvent(String symbols, WebSocketCallback<BookTickerEvent> callback) {
        throw new NotImplementedException("Not implemented");
    }

    public WebSocket onAllBookTickersEvent(WebSocketCallback<BookTickerEvent> callback) {
        return createNewWebSocket("!bookTicker", new BinanceApiWebSocketListener<>(callback, BookTickerEvent.class));
    }

    @Override
    public String toString() {
        return "FuturesWebSocketClient";
    }
}
