package com.binance.api.client.impl;

import com.binance.api.client.ApiRestClient;
import com.binance.api.client.config.BinanceApiConfig;
import com.binance.api.client.domain.SpotTransferType;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.general.*;
import com.binance.api.client.domain.market.*;

import java.util.List;

import static com.binance.api.client.GlobalConfig.getCurrentTime;
import static com.binance.api.client.config.BinanceApiConfig.*;
import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

/**
 * Implementation of Binance's REST API using Retrofit with synchronous/blocking method calls.
 */
public class SpotApiRestClientImpl implements ApiRestClient {
    protected final BinanceApiService binanceApiService;

    public SpotApiRestClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getSpotTestnetUrl() : getSpotBaseURL();
        binanceApiService = BinanceApiServiceGenerator.createService(BinanceApiService.class, url, apiKey, secret);
    }

    // General endpoints

    @Override
    public void ping() {
        executeSync(binanceApiService.ping());
    }

    @Override
    public Long getServerTime() {
        return executeSync(binanceApiService.getServerTime()).getServerTime();
    }

    @Override
    public ExchangeInfo getExchangeInfo() {
        return executeSync(binanceApiService.getExchangeInfo());
    }

    @Override
    public List<CoinInfo> getCoinsInfo() {
        return executeSync(binanceApiService.coinsInfo(DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public List<Asset> getAllAssets() {
        return executeSync(binanceApiService.getAllAssets(BinanceApiConfig.getAssetInfoApiBaseUrl() + "assetWithdraw/getAllAsset.html"));
    }

    @Override
    public AccountStatusResponse getAccountStatus() {
        return executeSync(binanceApiService.getAccountStatus(DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    // Market Data endpoints

    @Override
    public OrderBook getOrderBook(String symbol, Integer limit) {
        return executeSync(binanceApiService.getOrderBook(symbol, limit));
    }

    @Override
    public List<TradeHistoryItem> getTrades(String symbol, Integer limit) {
        return executeSync(binanceApiService.getTrades(symbol, limit));
    }

    @Override
    public List<TradeHistoryItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
        return executeSync(binanceApiService.getHistoricalTrades(symbol, limit, fromId));
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime) {
        return executeSync(binanceApiService.getAggTrades(symbol, fromId, limit, startTime, endTime));
    }

    @Override
    public List<AggTrade> getAggTrades(String symbol) {
        return getAggTrades(symbol, null, null, null, null);
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime) {
        return executeSync(binanceApiService.getCandlestickBars(symbol, interval.getIntervalId(), limit, startTime, endTime));
    }

    @Override
    public List<Candlestick> getCandlestickBars(String symbol, CandlestickInterval interval) {
        return getCandlestickBars(symbol, interval, null, null, null);
    }

    @Override
    public TickerStatistics get24HrPriceStatistics(String symbol) {
        return executeSync(binanceApiService.get24HrPriceStatistics(symbol));
    }

    @Override
    public List<TickerStatistics> getAll24HrPriceStatistics() {
        return executeSync(binanceApiService.getAll24HrPriceStatistics());
    }

    @Override
    public TickerPrice getPrice(String symbol) {
        return executeSync(binanceApiService.getLatestPrice(symbol));
    }

    @Override
    public List<TickerPrice> getAllPrices() {
        return executeSync(binanceApiService.getLatestPricesV3());
    }

    @Override
    public List<BookTicker> getBookTickers() {
        return executeSync(binanceApiService.getBookTickers());
    }

    @Override
    public BookTicker getBookTicker(String symbol) {
        return executeSync(binanceApiService.getBookTicker(symbol));
    }

    @Override
    public List<BookTicker> getBookTicker(List<String> symbols) {
        return executeSync(binanceApiService.getBookTicker(symbols));
    }

    public List<FeeStructure> getFeeStructure() {
        return executeSync(binanceApiService.getFeeStructure(null, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public NewOrderResponse newOrder(NewOrder order) {
        return executeSync(binanceApiService.newOrder(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getQuoteOrderQty(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(),
                order.getRecvWindow(), order.getTimestamp()));
    }

    @Override
    public void newOrderTest(NewOrder order) {
        executeSync(binanceApiService.newOrderTest(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getQuoteOrderQty(), order.getPrice(),
                order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(), order.getNewOrderRespType(),
                order.getRecvWindow(), order.getTimestamp()));
    }

    // Account endpoints

    @Override
    public Order getOrderStatus(OrderStatusRequest orderStatusRequest) {
        return executeSync(binanceApiService.getOrderStatus(orderStatusRequest.getSymbol(),
                orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest) {
        return executeSync(binanceApiService.cancelOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
                cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest) {
        return executeSync(binanceApiService.getOpenOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getAllOrders(AllOrdersRequest orderRequest) {
        return executeSync(binanceApiService.getAllOrders(orderRequest.getSymbol(),
                orderRequest.getOrderId(), orderRequest.getStartTime(), orderRequest.getEndTime(),
                orderRequest.getLimit(), orderRequest.getRecvWindow(), orderRequest.getTimestamp()));
    }

    @Override
    public NewOCOResponse newOCO(NewOCO oco) {
        return executeSync(binanceApiService.newOCO(oco.getSymbol(), oco.getListClientOrderId(), oco.getSide(),
                oco.getQuantity(), oco.getLimitClientOrderId(), oco.getPrice(), oco.getLimitIcebergQty(),
                oco.getStopClientOrderId(), oco.getStopPrice(), oco.getStopLimitPrice(), oco.getStopIcebergQty(),
                oco.getStopLimitTimeInForce(), oco.getNewOrderRespType(), oco.getRecvWindow(), oco.getTimestamp()));
    }

    @Override
    public CancelOrderListResponse cancelOrderList(CancelOrderListRequest cancelOrderListRequest) {
        return executeSync(binanceApiService.cancelOrderList(cancelOrderListRequest.getSymbol(), cancelOrderListRequest.getOrderListId(),
                cancelOrderListRequest.getListClientOrderId(), cancelOrderListRequest.getNewClientOrderId(),
                cancelOrderListRequest.getRecvWindow(), cancelOrderListRequest.getTimestamp()));
    }

    @Override
    public OrderList getOrderListStatus(OrderListStatusRequest orderListStatusRequest) {
        return executeSync(binanceApiService.getOrderListStatus(orderListStatusRequest.getOrderListId(), orderListStatusRequest.getOrigClientOrderId(),
                orderListStatusRequest.getRecvWindow(), orderListStatusRequest.getTimestamp()));
    }

    @Override
    public List<OrderList> getAllOrderList(AllOrderListRequest allOrderListRequest) {
        return executeSync(binanceApiService.getAllOrderList(allOrderListRequest.getFromId(), allOrderListRequest.getStartTime(),
                allOrderListRequest.getEndTime(), allOrderListRequest.getLimit(), allOrderListRequest.getRecvWindow(), allOrderListRequest.getTimestamp()));
    }

    @Override
    public Account getAccount(Long recvWindow, Long timestamp) {
        return executeSync(binanceApiService.getAccount(recvWindow, timestamp));
    }

    @Override
    public Account getAccount() {
        return getAccount(DEFAULT_RECEIVING_WINDOW, getCurrentTime());
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp) {
        return executeSync(binanceApiService.getMyTrades(symbol, limit, fromId, recvWindow, timestamp));
    }

    @Override
    public List<Trade> getMyTrades(String symbol, Integer limit) {
        return getMyTrades(symbol, limit, null, DEFAULT_RECEIVING_WINDOW, getCurrentTime());
    }

    @Override
    public List<Trade> getMyTrades(String symbol) {
        return getMyTrades(symbol, null, null, DEFAULT_RECEIVING_WINDOW, getCurrentTime());
    }

    @Override
    public WithdrawResult withdraw(String asset, String address, String network, String amount, String name, String addressTag) {
        return executeSync(binanceApiService.withdraw(asset, address, network, amount, name, addressTag, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public DustTransferResponse dustTransfer(List<String> asset) {
        return executeSync(binanceApiService.dustTransfer(asset, DEFAULT_RECEIVING_WINDOW, System.currentTimeMillis()));
    }

    @Override
    public DepositHistory getDepositHistory(String asset) {
        return executeSync(binanceApiService.getDepositHistory(asset, DEFAULT_RECEIVING_WINDOW,
                System.currentTimeMillis()));
    }

    @Override
    public WithdrawHistory getWithdrawHistory(String asset) {
        return executeSync(binanceApiService.getWithdrawHistory(asset, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public DepositAddress getDefaultDepositAddress(String asset) {
        return executeSync(binanceApiService.getDefaultDepositAddress(asset, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public DepositAddress getDepositAddress(DepositAddressRequest request) {
        return executeSync(binanceApiService.getDepositAddress(request.getCoin(), request.getNetwork(), DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public Transaction transfer(String asset, String amount, SpotTransferType type) {
        return executeSync(binanceApiService.transferToFutures(asset, amount, type, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public Transaction universalTransfer(UniversalTransferRequest request) {
        return executeSync(binanceApiService.universalTransfer(request.getType(), request.getAsset(), request.getAmount().toPlainString(),
                request.getFromSymbol(), request.getToSymbol(), request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public DustTransferResponse convertToBNB(List<String> assets) {
        return executeSync(binanceApiService.dustTransfer(assets, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    @Override
    public SwapRecord convertFunds(String quoteAsset, String baseAsset, String quoteQty) {
        return executeSync(binanceApiService.swap(quoteAsset, baseAsset, quoteQty, DEFAULT_RECEIVING_WINDOW, getCurrentTime()));
    }

    // User stream endpoints

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
}
