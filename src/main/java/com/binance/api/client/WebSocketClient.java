package com.binance.api.client;

import com.binance.api.client.domain.event.*;
import com.binance.api.client.domain.market.CandlestickInterval;
import okhttp3.WebSocket;
import org.apache.commons.lang3.NotImplementedException;

import java.io.Closeable;
import java.util.List;

/**
 * Binance API data streaming facade, supporting streaming of events through web sockets.
 */
public interface WebSocketClient {

    /**
     * Open a new web socket to receive {@link DepthEvent depthEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onDepthEvent(String symbols, WebSocketCallback<DepthEvent> callback);

   default WebSocket onPartialDepthEvent(String symbols, int depth, WebSocketCallback<PartialDepthEvent> callback){
       throw new NotImplementedException("Not implemented");
   }

    /**
     * Open a new web socket to receive {@link CandlestickEvent candlestickEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param interval the interval of the candles tick events required
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onCandlestickEvent(String symbols, CandlestickInterval interval, WebSocketCallback<CandlestickEvent> callback);

    /**
     * Open a new web socket to receive {@link AggTradeEvent aggTradeEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onAggTradeEvent(String symbols, WebSocketCallback<AggTradeEvent> callback);

    /**
     * Open a new web socket to receive {@link UserDataUpdateEvent userDataUpdateEvents} on a callback.
     *
     * @param listenKey the listen key to subscribe to.
     * @param callback  the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onUserDataUpdateEvent(String listenKey, WebSocketCallback<UserDataUpdateEvent> callback);

    /**
     * Open a new web socket to receive {@link TickerEvent tickerEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onTickerEvent(String symbols, WebSocketCallback<TickerEvent> callback);

    /**
     * Open a new web socket to receive {@link List<TickerEvent> allMarketTickersEvents} on a callback.
     *
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onAllMarketTickersEvent(WebSocketCallback<List<TickerEvent>> callback);

    /**
     * Open a new web socket to receive {@link BookTickerEvent bookTickerEvents} on a callback.
     *
     * @param symbols  market (one or coma-separated) symbol(s) to subscribe to
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onBookTickerEvent(String symbols, WebSocketCallback<BookTickerEvent> callback);

    /**
     * Open a new web socket to receive {@link TickerEvent allBookTickersEvents} on a callback.
     *
     * @param callback the callback to call on new events
     *
     * @return a {@link Closeable} that allows the underlying web socket to be closed.
     */
    WebSocket onAllBookTickersEvent(WebSocketCallback<BookTickerEvent> callback);
}
