package com.binance.api.client;

import com.binance.api.client.domain.FundingInfo;
import com.binance.api.client.domain.MarginChangeType;
import com.binance.api.client.domain.MarginType;
import com.binance.api.client.domain.PositionSide;
import com.binance.api.client.domain.account.FuturesAccountBalance;
import com.binance.api.client.domain.account.FuturesOrder;
import com.binance.api.client.domain.account.FuturesPosition;
import com.binance.api.client.domain.account.NewOrderResponseType;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.TickerPrice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface FuturesRestClient extends BinWebSocketManager {

    ExchangeInfo getExchangeInfo();

    //Trade
    FuturesOrder newOrder(FuturesOrder orderRequest, NewOrderResponseType responseType);

    FuturesOrder getOrder(String symbol, Long orderId, String clientOrderId);

    List<FuturesOrder> getOrders(String symbol, Integer count, Long orderId, Long startTime, Long endTime);

    FuturesOrder cancelOrder(CancelOrderRequest order);

    StatusResponse cancelAllOrders(String symbol);

    //Account
    List<FuturesAccountBalance> getAccountBalance();

    FuturesAccountInfo getAccountInfo();

    ChangeLeverageResponse changeAccountLeverage(String symbol, Integer leverage);

    StatusResponse changeMarginType(String symbol, MarginType marginType);

    List<FuturesPosition> getPositionDetails(String symbol);

    StatusResponse updatePositionMargin(String symbol, PositionSide side, BigDecimal amount, MarginChangeType changeType);

    StatusResponse setHedgeMode(boolean hedgeModeEnabled);

    List<FuturesTradeHistoryItem> getTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit);

    List<IncomeHistoryItem> getIncomeHistory(String symbol, FuturesIncomeType incomeType, Instant startTime, Instant endTime, int limit);

    List<FuturesAllOpenOrdersResponse> getAllOpenOrders(String symbol);

    TickerPrice getSymbolPrice(String symbol);

    Map<String, FundingInfo> getFundingInfo();

    Long getServerTime();
}
