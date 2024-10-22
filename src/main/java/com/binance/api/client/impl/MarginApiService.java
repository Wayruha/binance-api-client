package com.binance.api.client.impl;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.margin.*;
import com.binance.api.client.domain.account.request.AvailableAmountResponse;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.MaxTransferableResponse;
import com.binance.api.client.domain.event.ListenKey;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Margin Account endpoints
 */
public interface MarginApiService {

    //weight: 10
    @GET("/sapi/v1/margin/asset")
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<CrossMarginAssetInfo> queryCrossMarginAsset(@Query("asset") String asset);

    //weight: 10
    @GET("/sapi/v1/margin/pair")
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<MarginPairInfo> queryCrossMarginPair(@Query("symbol") String symbol);

    //weight: 1
    @GET("/sapi/v1/margin/allAssets")
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<List<CrossMarginAssetInfo>> queryAllCrossMarginAssets();

    //weight: 1
    @GET("/sapi/v1/margin/allPairs")
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    Call<List<MarginPairInfo>> queryAllCrossMarginPairs();

    /**
     * @param symbols Comma-separated symbols
     */
    @GET("/sapi/v1/margin/isolated/account")
    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    Call<IsolatedMarginAccount> queryIsolatedMarginAccount(@Query("symbols") String symbols,
                                                           @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @GET("/sapi/v1/margin/isolated/pair")
    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    Call<MarginPairInfo> queryIsolatedMarginSymbol(@Query("symbol") String symbol,
                                                   @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @GET("/sapi/v1/margin/isolated/allPairs")
    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    Call<List<MarginPairInfo>> queryAllIsolatedMarginSymbols(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/margin/transfer")
    Call<Transaction> marginTransfer(@Query("asset") String asset, @Query("amount") String amount, @Query("type") String type,
                                     @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/margin/loan")
    Call<Transaction> borrow(@Query("asset") String asset, @Query("isIsolated") boolean isIsolated, @Query("symbol") String symbol, @Query("amount") String amount, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/loan")
    Call<LoanQueryResult> queryLoan(@Query("asset") String asset, @Query("txId") String txId, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/repay")
    Call<RepayQueryResult> queryRepay(@Query("asset") String asset, @Query("txId") String txId, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/maxBorrowable")
    Call<MaxBorrowableQueryResult> queryMaxBorrowable(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/maxBorrowable")
    Call<MaxBorrowableQueryResult> queryMaxBorrowable(@Query("asset") String asset, @Query("isolatedSymbol") String isolatedSymbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/crossMarginData")
    Call<List<CrossMarginDataResponse>> getCrossMarginData(@Query("vipLevel") String vipLevel, @Query("coin") String coin, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/isolatedMarginData ")
    Call<List<IsolatedMarginDataResponse>> getIsolatedMarginData(@Query("vipLevel") String vipLevel, @Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/maxTransferable")
    Call<MaxTransferableResponse> queryMaxTransferable(@Query("asset") String asset, @Query("isolatedSymbol") String isolatedSymbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/repay")
    Call<RepayQueryResult> queryRepay(@Query("asset") String asset, @Query("startTime") Long starttime, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/margin/repay")
    Call<Transaction> repay(@Query("asset") String asset, @Query("isIsolated") boolean isIsolated, @Query("symbol") String symbol, @Query("amount") String amount, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/account")
    Call<MarginAccount> getMarginAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/margin/openOrders")
    Call<List<Order>> getOpenMarginOrders(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/margin/order")
    Call<MarginNewOrderResponse> newMarginOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                                                @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity,
                                                @Query("quoteOrderQty") String quoteOrderQty,
                                                @Query("price") String price, @Query("newClientOrderId") String newClientOrderId,
                                                @Query("stopPrice") String stopPrice, @Query("icebergQty") String icebergQty,
                                                @Query("newOrderRespType") NewOrderResponseType newOrderRespType, @Query("sideEffectType") SideEffectType sideEffectType,
                                                @Query("isIsolated") String isIsolated, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/sapi/v1/margin/order")
    Call<CancelOrderResponse> cancelMarginOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId, @Query("isIsolated") String isIsolated,
                                                @Query("origClientOrderId") String origClientOrderId, @Query("newClientOrderId") String newClientOrderId,
                                                @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/order")
    Call<Order> getMarginOrderStatus(@Query("symbol") String symbol, @Query("orderId") Long orderId, @Query("isIsolated") String isIsolated,
                                     @Query("origClientOrderId") String origClientOrderId, @Query("recvWindow") Long recvWindow,
                                     @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/allOrders")
    Call<List<Order>> getMarginAllOrders(@Query("symbol") String symbol, @Query("isIsolated") boolean isIsolated, @Query("orderId") Long orderId,
                                   @Query("startTime") Long startTime, @Query("endTime") Long endTime, @Query("limit") Integer limit,
                                   @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/myTrades")
    Call<List<Trade>> getMyMarginTrades(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("fromId") Long fromId,
                                        @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/margin/maxTransferable")
    Call<AvailableAmountResponse> getMaxTransferOutAmount(@Query("symbol") String symbol, @Query("isolatedSymbol") String isolatedSymbol,
                                                          @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);


    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/sapi/v1/userDataStream")
    Call<ListenKey> startMarginUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/sapi/v1/userDataStream")
    Call<Void> keepAliveMarginUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/sapi/v1/userDataStream")
    Call<Void> deleteMarginUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/sapi/v1/userDataStream/isolated")
    Call<ListenKey> startIsolatedMarginUserDataStream();

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/sapi/v1/userDataStream/isolated")
    Call<Void> keepAliveIsolatedMarginUserDataStream(@Query("listenKey") String listenKey);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/sapi/v1/userDataStream/isolated")
    Call<Void> deleteIsolatedMarginUserDataStream(@Query("listenKey") String listenKey);
}
