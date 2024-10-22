package com.binance.api.client.impl;

import com.binance.api.client.GlobalConfig;
import com.binance.api.client.MarginApiClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.margin.*;
import com.binance.api.client.domain.account.request.*;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.binance.api.client.config.BinanceApiConfig.getSpotBaseURL;
import static com.binance.api.client.config.BinanceApiConfig.getSpotTestnetUrl;
import static com.binance.api.client.constant.BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
import static com.binance.api.client.impl.BinanceApiServiceGenerator.executeSync;

/**
 * Implementation of Binance's Margin REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class MarginApiClientImpl implements MarginApiClient {
    private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";

    private final MarginApiService marginApiService;

    public MarginApiClientImpl(String apiKey, String secret, boolean isTestnet) {
        final String url = isTestnet ? getSpotTestnetUrl() : getSpotBaseURL();
        marginApiService = BinanceApiServiceGenerator.createService(MarginApiService.class, url, apiKey, secret);
    }

    @Override
    public MarginAccount getAccount() {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.getMarginAccount(DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public MarginPairInfo queryCrossMarginPair(String symbol) {
        return executeSync(marginApiService.queryCrossMarginPair(symbol));
    }

    @Override
    public CrossMarginAssetInfo queryCrossMarginAsset(String symbol) {
        return executeSync(marginApiService.queryCrossMarginAsset(symbol));
    }

    @Override
    public List<MarginPairInfo> queryCrossMarginPairs() {
        return executeSync(marginApiService.queryAllCrossMarginPairs());
    }

    @Override
    public IsolatedMarginAccount getIsolatedMarginAccount(String... symbols) {
        final String joinedSymbols = Arrays.stream(symbols).collect(Collectors.joining(","));
        return executeSync(marginApiService.queryIsolatedMarginAccount(joinedSymbols, DEFAULT_RECEIVING_WINDOW, GlobalConfig.getCurrentTime()));
    }

    @Override
    public MarginPairInfo queryIsolatedMarginSymbol(String symbol) {
        return executeSync(marginApiService.queryIsolatedMarginSymbol(symbol, DEFAULT_RECEIVING_WINDOW, GlobalConfig.getCurrentTime()));
    }

    @Override
    public List<MarginPairInfo> queryIsolatedMarginSymbol() {
        return executeSync(marginApiService.queryAllIsolatedMarginSymbols(DEFAULT_RECEIVING_WINDOW, GlobalConfig.getCurrentTime()));
    }

    @Override
    public List<CrossMarginAssetInfo> queryCrossMarginAssets() {
        return executeSync(marginApiService.queryAllCrossMarginAssets());
    }

    @Override
    public List<Order> getOpenOrders(OrderRequest orderRequest) {
        return executeSync(marginApiService.getOpenMarginOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()));
    }

    @Override
    public List<Order> getAllOrders(MarginAllOrdersRequest request) {
        return executeSync(marginApiService.getMarginAllOrders(request.getSymbol(), request.isIsolated(),
                request.getOrderId(), request.getStartTime(), request.getEndTime(), request.getLimit(),
                request.getRecvWindow(), request.getTimestamp()));
    }

    @Override
    public MarginNewOrderResponse newOrder(MarginNewOrder order) {
        final String isIsolated = order.isIsolated() ? "TRUE" : "FALSE";
        return executeSync(marginApiService.newMarginOrder(order.getSymbol(), order.getSide(), order.getType(),
                order.getTimeInForce(), order.getQuantity(), order.getQuoteOrderQty(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(),
                order.getIcebergQty(), order.getNewOrderRespType(), order.getSideEffectType(), isIsolated, order.getRecvWindow(), order.getTimestamp()));
    }

    @Override
    public CancelOrderResponse cancelOrder(CancelMarginOrderRequest req) {
        final String isIsolated = req.isIsolated() ? TRUE : FALSE;
        return executeSync(marginApiService.cancelMarginOrder(req.getSymbol(),
                req.getOrderId(), isIsolated, req.getOrigClientOrderId(), req.getNewClientOrderId(),
                req.getRecvWindow(), req.getTimestamp()));
    }

    @Override
    public Order getOrderStatus(MarginOrderStatusRequest req) {
        final String isIsolated = req.isIsolated() ? TRUE : FALSE;
        return executeSync(marginApiService.getMarginOrderStatus(req.getSymbol(), req.getOrderId(), isIsolated,
                req.getOrigClientOrderId(), req.getRecvWindow(), req.getTimestamp()));
    }

    @Override
    public Transaction transfer(String asset, String amount, TransferType type) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.marginTransfer(asset, amount, type.getValue(), DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public Transaction borrow(MarginLoanRequest request) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.borrow(request.getAsset(), request.isIsolated(), request.getSymbol(), request.getAmount().toPlainString(), DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public MaxTransferableResponse queryMaxTransferable(MaxTransferableRequest request) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryMaxTransferable(request.getAsset(), request.getIsolatedSymbol(), DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public LoanQueryResult queryLoan(String asset, String txId) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryLoan(asset, txId, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public RepayQueryResult queryRepay(String asset, String txId) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryRepay(asset, txId, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public RepayQueryResult queryRepay(String asset, long startTime) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryRepay(asset, startTime, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public MaxBorrowableQueryResult queryMaxBorrowable(String asset) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryMaxBorrowable(asset, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public List<CrossMarginDataResponse> getCrossMarginData(String vipLevel, String coin) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.getCrossMarginData(vipLevel, coin, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public List<IsolatedMarginDataResponse> getIsolatedMarginData(String vipLevel, String symbol) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.getIsolatedMarginData(vipLevel, symbol, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public MaxBorrowableQueryResult queryMaxBorrowable(String asset, String isolatedSymbol) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.queryMaxBorrowable(asset, isolatedSymbol, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public Transaction repay(String asset, boolean isIsolated, String symbol, String amount) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.repay(asset, isIsolated, symbol, amount, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    @Override
    public AvailableAmountResponse getMaxTransferOutAmount(String asset, String isolatedPair) {
        long timestamp = GlobalConfig.getCurrentTime();
        return executeSync(marginApiService.getMaxTransferOutAmount(asset, isolatedPair, DEFAULT_RECEIVING_WINDOW, timestamp));
    }

    // user stream endpoints
    @Override
    public String startUserDataStream() {
        return executeSync(marginApiService.startMarginUserDataStream()).toString();
    }

    @Override
    public void keepAliveUserDataStream(String listenKey) {
        executeSync(marginApiService.keepAliveMarginUserDataStream(listenKey));
    }

    @Override
    public void closeUserDataStream(String listenKey) {
        executeSync(marginApiService.deleteMarginUserDataStream(listenKey));
    }
}