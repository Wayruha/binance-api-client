package com.binance.api.client.impl;

import com.binance.api.client.*;
import com.binance.api.client.domain.event.*;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.binance.api.client.config.BinanceApiConfig.*;
import static java.lang.String.format;

/**
 * Binance API WebSocket client implementation using OkHttp.
 */
public class SpotWebSocketClient implements WebSocketClient {
    protected static final int WS_DEPTH_UPDATE_RATE_MS=250;
    protected static final List<Integer> VALID_ORDER_BOOK_DEPTH = List.of(5, 10, 20);
    protected final OkHttpClient client;
    protected final boolean isTestnet;

    public SpotWebSocketClient(final OkHttpClient client, final boolean isTestnet) {
        this.client = client;
        this.isTestnet = isTestnet;
    }

    protected WebSocket createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
        final String baseUrl = isTestnet ? getSpotStreamTestnetUrl() : getSpotStreamUrl();
        String streamingUrl = format("%s/%s", baseUrl, channel);
        Request request = new Request.Builder().url(streamingUrl).build();
        return client.newWebSocket(request, listener);
    }

    @Override
    public WebSocket onDepthEvent(String symbols, WebSocketCallback<DepthEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                                       .map(String::trim)
                                       .map(s -> format("%s@depth", s))
                                       .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, DepthEvent.class));
    }

    @Override
    public WebSocket onCandlestickEvent(String symbols, CandlestickInterval interval, WebSocketCallback<CandlestickEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                                       .map(String::trim)
                                       .map(s -> format("%s@kline_%s", s, interval.getIntervalId()))
                                       .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
    }

    public WebSocket onAggTradeEvent(String symbols, WebSocketCallback<AggTradeEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                                       .map(String::trim)
                                       .map(s -> format("%s@aggTrade", s))
                                       .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, AggTradeEvent.class));
    }

    public WebSocket onUserDataUpdateEvent(String listenKey, WebSocketCallback<UserDataUpdateEvent> callback) {
        return createNewWebSocket(listenKey, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
    }

    @Override
    public WebSocket onTickerEvent(String symbols, WebSocketCallback<TickerEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                                       .map(String::trim)
                                       .map(s -> format("%s@ticker", s))
                                       .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, TickerEvent.class));
    }

    public WebSocket onAllMarketTickersEvent(WebSocketCallback<List<TickerEvent>> callback) {
        final String channel = "!ticker@arr";
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, new TypeReference<>() {
        }));
    }

    @Override
    public WebSocket onBookTickerEvent(String symbols, WebSocketCallback<BookTickerEvent> callback) {
        final String channel = Arrays.stream(symbols.split(","))
                                       .map(String::trim)
                                       .map(s -> format("%s@bookTicker", s))
                                       .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, BookTickerEvent.class));
    }

    public WebSocket onAllBookTickersEvent(WebSocketCallback<BookTickerEvent> callback) {
        final String channel = "!bookTicker";
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, BookTickerEvent.class));
    }

    @Override
    public WebSocket onPartialDepthEvent(String symbols, int depth, WebSocketCallback<PartialDepthEvent> callback) {
        if(!VALID_ORDER_BOOK_DEPTH.contains(depth)) throw new IllegalArgumentException(format("Depth %s is not valid. Valid values: %s", depth, VALID_ORDER_BOOK_DEPTH));
        final String channel = Arrays.stream(symbols.split(","))
                .map(String::trim)
                .map(s -> format("%s@depth%s", s, depth))
                .collect(Collectors.joining("/"));
        return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, PartialDepthEvent.class));
    }

    @Override
    public String toString() {
        return "SpotWebSocketClient";
    }
}
