package com.binance.api.client.impl;

import com.binance.api.client.*;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.TickerPrice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static com.binance.api.client.GlobalConfig.getCurrentTime;
import static com.binance.api.client.config.BinanceApiConfig.*;
import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.*;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class FuturesRestClientImpl implements FuturesRestClient {
    protected final BinanceFuturesEndpoints binanceApiService;

    public FuturesRestClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getFuturesTestnetUrl() : getFuturesBaseURL();
        binanceApiService = createService(BinanceFuturesEndpoints.class, url, apiKey, secret);
    }

    //Web Socket
    @Override
    public String startUserDataStream() {
        return executeSync(binanceApiService.startUserDataStream()).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {
        executeSync(binanceApiService.keepAliveUserDataStream(listenKey));
    }

    @Override
    public void closeUserDataStream(String listenKey) {
        executeSync(binanceApiService.closeAliveUserDataStream(listenKey));
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        return executeSync(binanceApiService.getExchangeInfo());
    }

    //Trade

    @Override
    public FuturesOrder newOrder(FuturesOrder order, NewOrderResponseType responseType) {
        return executeSync(binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getPositionSide(), order.getType(),
                order.getTimeInForce(), order.getOriginalQuantity(), order.getReduceOnly(), order.getPrice(), order.getNewClientOrderId(),
                order.getStopPrice(), order.getClosePosition(), order.getActivatePrice(), order.getPriceRate(),
                order.getWorkingType(), order.getPriceProtect(), responseType, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public FuturesOrder getOrder(String symbol, Long orderId, String clientOrderId) {
        return executeSync(binanceApiService.queryOrder(symbol, orderId, clientOrderId,
                DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<FuturesOrder> getOrders(String symbol, Integer count, Long orderId, Long startTime, Long endTime) {
        if (symbol == null || count == null) throw new IllegalArgumentException("Symbol and Count can't be null");
        return executeSync(binanceApiService.getAllOrders(symbol, orderId, startTime, endTime,
                count, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public FuturesOrder cancelOrder(CancelOrderRequest order) {
        return executeSync(binanceApiService.cancelOrder(order.getSymbol(), order.getOrderId(), order.getNewClientOrderId(),
                DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public StatusResponse cancelAllOrders(String symbol) {
        return executeSync(binanceApiService.cancelAllOpenOrder(symbol, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    //Account

    @Override
    public List<FuturesAccountBalance> getAccountBalance() {
        return executeSync(binanceApiService.accountBalance(DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public FuturesAccountInfo getAccountInfo() {
        return executeSync(binanceApiService.accountInfo(DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<FuturesTradeHistoryItem> getTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit) {
        if (symbol == null)
            throw new IllegalArgumentException("Symbol can't be null");
        return executeSync(binanceApiService.userTrades(symbol, startTime, endTime, fromId, limit, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public ChangeLeverageResponse changeAccountLeverage(String symbol, Integer leverage) {
        if (symbol == null || leverage == null) throw new IllegalArgumentException("Symbol and Leverage can't be null");
        return executeSync(binanceApiService.changeInitialLeverage(symbol, leverage, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public StatusResponse changeMarginType(String symbol, MarginType marginType) {
        if (symbol == null || marginType == null)
            throw new IllegalArgumentException("Symbol and MarginType can't be null");
        return executeSync(binanceApiService.changeMarginType(symbol, marginType, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public StatusResponse updatePositionMargin(String symbol, PositionSide side, BigDecimal amount, MarginChangeType changeType) {
        if (symbol == null || side == null || amount == null || changeType == null)
            throw new IllegalArgumentException("Some fields are null: " + symbol + "," + side + "," + amount + "," + changeType);
        return executeSync(binanceApiService.changePositionMargin(symbol, side, amount.toPlainString(), changeType, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<FuturesPosition> getPositionDetails(String symbol) {
        if (symbol == null) throw new IllegalArgumentException("Symbol can't be null");
        return executeSync(binanceApiService.positionInformation(symbol, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public StatusResponse setHedgeMode(boolean hedgeModeEnabled) {
        return executeSync(binanceApiService.setPositionSide(hedgeModeEnabled, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<IncomeHistoryItem> getIncomeHistory(String symbol, FuturesIncomeType incomeType, Instant startTime, Instant endTime, int limit) {
        return executeSync(binanceApiService.incomeHistory(symbol, incomeType, startTime != null ? startTime.toEpochMilli() : null,
                endTime != null ? endTime.toEpochMilli() : null, limit,
                DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<FuturesAllOpenOrdersResponse> getAllOpenOrders(String symbol) {
        return executeSync(binanceApiService.allOpenOrders(symbol, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public TickerPrice getSymbolPrice(String symbol) {
        return executeSync(binanceApiService.getSymbolPrice(symbol));
    }
}
