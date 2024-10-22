package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiAsyncMarginRestClient;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.margin.MarginAccount;
import com.binance.api.client.domain.account.margin.MarginNewOrder;
import com.binance.api.client.domain.account.margin.MarginNewOrderResponse;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.event.ListenKey;

import java.util.List;

import static com.binance.api.client.config.BinanceApiConfig.getSpotBaseURL;
import static com.binance.api.client.config.BinanceApiConfig.getSpotTestnetUrl;

/**
 * Implementation of Binance's Margin REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class BinanceApiAsyncMarginRestClientImpl implements BinanceApiAsyncMarginRestClient {
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";

    private final MarginApiService marginApiService;

    public BinanceApiAsyncMarginRestClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getSpotTestnetUrl() : getSpotBaseURL();
        marginApiService = BinanceApiServiceGenerator.createService(MarginApiService.class, url, apiKey, secret);
    }

    // Margin Account endpoints

    @Override
    public void getAccount(Long recvWindow, Long timestamp, BinanceApiCallback<MarginAccount> callback) {
        marginApiService.getMarginAccount(recvWindow, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAccount(BinanceApiCallback<MarginAccount> callback) {
        long timestamp = System.currentTimeMillis();
        marginApiService.getMarginAccount(BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOpenOrders(OrderRequest orderRequest, BinanceApiCallback<List<Order>> callback) {
        marginApiService.getOpenMarginOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void newOrder(MarginNewOrder order, BinanceApiCallback<MarginNewOrderResponse> callback) {
        final String isIsolated = order.isIsolated() ? TRUE : FALSE;
        marginApiService.newMarginOrder(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(),
                order.getQuantity(), order.getQuoteOrderQty(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(),
                order.getNewOrderRespType(), order.getSideEffectType(), isIsolated, order.getRecvWindow(), order.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelOrder(CancelMarginOrderRequest req, BinanceApiCallback<CancelOrderResponse> callback) {
        final String isIsolated = req.isIsolated() ? TRUE : FALSE;
        marginApiService.cancelMarginOrder(req.getSymbol(), req.getOrderId(),
                req.getOrigClientOrderId(), req.getNewClientOrderId(), isIsolated,
                req.getRecvWindow(), req.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOrderStatus(MarginOrderStatusRequest req, BinanceApiCallback<Order> callback) {
        final String isIsolated = req.isIsolated() ? TRUE : FALSE;
        marginApiService.getMarginOrderStatus(req.getSymbol(),req.getOrderId(), isIsolated,
                req.getOrigClientOrderId(), req.getRecvWindow(), req.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    // user stream endpoints

    @Override
    public void startUserDataStream(BinanceApiCallback<ListenKey> callback) {
        marginApiService.startMarginUserDataStream().enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void keepAliveUserDataStream(String listenKey, BinanceApiCallback<Void> callback) {
        marginApiService.keepAliveMarginUserDataStream(listenKey).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void transfer(String asset, String amount, TransferType type, BinanceApiCallback<Transaction> callback) {
        long timestamp = System.currentTimeMillis();
        marginApiService.marginTransfer(asset, amount, type.getValue(), BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void borrow(String asset, boolean isIsolated, String symbol, String amount, BinanceApiCallback<Transaction> callback) {
        long timestamp = System.currentTimeMillis();
        marginApiService.borrow(asset, isIsolated, symbol, amount, BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void repay(String asset, boolean isIsolated, String symbol, String amount, BinanceApiCallback<Transaction> callback) {
        long timestamp = System.currentTimeMillis();
        marginApiService.repay(asset, isIsolated, symbol, amount, BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }
}
