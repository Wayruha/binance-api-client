package com.binance.api.client;

import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.RepayQueryResult;
import com.binance.api.client.domain.account.Transaction;
import com.binance.api.client.domain.account.margin.*;
import com.binance.api.client.domain.account.request.*;

import java.util.List;

public interface MarginApiClient extends BinWebSocketManager {
    /**
     * Get current margin account information using default parameters.
     */
    MarginAccount getAccount();

    /**
     * Query Cross Margin Pair
     */
    MarginPairInfo queryCrossMarginPair(String symbol);

    CrossMarginAssetInfo queryCrossMarginAsset(String symbol);

    /**
     * Get All Cross Margin Pairs
     */
    List<MarginPairInfo> queryCrossMarginPairs();

    IsolatedMarginAccount getIsolatedMarginAccount(String... symbols);

    MarginPairInfo queryIsolatedMarginSymbol(String symbol);

    List<MarginPairInfo> queryIsolatedMarginSymbol();

    /**
     * Get All Cross Margin Assets
     */
    List<CrossMarginAssetInfo> queryCrossMarginAssets();

    /**
     * Get all open orders on margin account for a symbol.
     *
     * @param orderRequest order request parameters
     */
    List<Order> getOpenOrders(OrderRequest orderRequest);

    List<Order> getAllOrders(MarginAllOrdersRequest request);

    /**
     * Send in a new margin order.
     *
     * @param order the new order to submit.
     * @return a response containing details about the newly placed order.
     */
    MarginNewOrderResponse newOrder(MarginNewOrder order);

    /**
     * Cancel an active margin order.
     *
     * @param cancelOrderRequest order status request parameters
     */
    CancelOrderResponse cancelOrder(CancelMarginOrderRequest cancelOrderRequest);

    /**
     * Check margin order's status.
     *
     * @param orderStatusRequest order status request options/filters
     * @return an order
     */
    Order getOrderStatus(MarginOrderStatusRequest orderStatusRequest);

    /**
     * Execute transfer between spot account and margin account
     *
     * @param asset  asset to repay
     * @param amount amount to repay
     * @return transaction id
     */
    Transaction transfer(String asset, String amount, TransferType type);

    /**
     * Apply for a loan
     *
     * @param request request dto
     * @return transaction id
     */
    Transaction borrow(MarginLoanRequest request);

    MaxTransferableResponse queryMaxTransferable(MaxTransferableRequest request);

    /**
     * Query loan record
     *
     * @param asset asset to query
     * @return repay records
     */
    RepayQueryResult queryRepay(String asset, long startTime);

    /**
     * Query max borrowable
     *
     * @param asset asset to query
     * @return max borrowable
     */
    MaxBorrowableQueryResult queryMaxBorrowable(String asset);

    List<CrossMarginDataResponse> getCrossMarginData(String vipLevel, String coin);

    List<IsolatedMarginDataResponse> getIsolatedMarginData(String vipLevel, String symbol);

    MaxBorrowableQueryResult queryMaxBorrowable(String asset, String isolatedSymbol);


    /**
     * Query loan record
     *
     * @param asset asset to query
     * @param txId  the tranId in POST /sapi/v1/margin/repay
     * @return loan records
     */
    RepayQueryResult queryRepay(String asset, String txId);

    /**
     * Repay loan for margin account
     *
     * @param asset      asset to repay
     * @param isIsolated use cross or isolated margin
     * @param symbol     isolated symbol
     * @param amount     amount to repay
     * @return transaction id
     */
    Transaction repay(String asset, boolean isIsolated, String symbol, String amount);

    /**
     * Query loan record
     *
     * @param asset asset to query
     * @param txId  the tranId in POST /sapi/v1/margin/loan
     * @return loan records
     */
    LoanQueryResult queryLoan(String asset, String txId);

    AvailableAmountResponse getMaxTransferOutAmount(String asset, String isolatedPair);
}
