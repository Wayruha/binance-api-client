package com.binance.api.client.impl;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.*;
import com.binance.api.client.domain.market.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Binance's REST API URL mappings and endpoint security configuration.
 */
public interface BinanceApiService {
    // General endpoints

    @GET("/api/v1/ping")
    Call<Void> ping();

    @GET("/api/v1/time")
    Call<ServerTime> getServerTime();

    //api weight: 10
    @GET("/api/v3/exchangeInfo")
    Call<ExchangeInfo> getExchangeInfo();

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/capital/config/getall")
    Call<List<CoinInfo>> coinsInfo(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @GET
    Call<List<Asset>> getAllAssets(@Url String url);

    // Market data endpoints

    @GET("/api/v1/depth")
    Call<OrderBook> getOrderBook(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @GET("/api/v1/trades")
    Call<List<TradeHistoryItem>> getTrades(@Query("symbol") String symbol, @Query("limit") Integer limit);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @GET("/api/v1/historicalTrades")
    Call<List<TradeHistoryItem>> getHistoricalTrades(@Query("symbol") String symbol, @Query("limit") Integer limit,
                                                     @Query("fromId") Long fromId);

    @GET("/api/v1/aggTrades")
    Call<List<AggTrade>> getAggTrades(@Query("symbol") String symbol, @Query("fromId") String fromId, @Query("limit") Integer limit,
                                      @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    @GET("/api/v1/klines")
    Call<List<Candlestick>> getCandlestickBars(@Query("symbol") String symbol, @Query("interval") String interval, @Query("limit") Integer limit,
                                               @Query("startTime") Long startTime, @Query("endTime") Long endTime);

    @GET("/api/v1/ticker/24hr")
    Call<TickerStatistics> get24HrPriceStatistics(@Query("symbol") String symbol);

    @GET("/api/v1/ticker/24hr")
    Call<List<TickerStatistics>> getAll24HrPriceStatistics();

    @GET("/api/v1/ticker/allPrices")
    Call<List<TickerPrice>> getLatestPrices();

    //api weight: 1 for a single symbol; 2 when the symbol parameter is omitted;
    @GET("/api/v3/ticker/price")
    Call<TickerPrice> getLatestPrice(@Query("symbol") String symbol);

    //api weight: 1 for a single symbol; 2 when the symbol parameter is omitted;
    @GET("/api/v3/ticker/price")
    Call<List<TickerPrice>> getLatestPricesV3();

    @GET("/api/v1/ticker/allBookTickers")
    Call<List<BookTicker>> getBookTickers();

    //api weight: 1 for a single symbol; 2 when the symbol parameter is omitted;
    @GET("/api/v3/ticker/bookTicker")
    Call<BookTicker> getBookTicker(@Query("symbol") String symbol);

    @GET("/api/v3/ticker/bookTicker")
    Call<List<BookTicker>> getBookTicker(@Query("symbols") List<String> symbol);

    // Account endpoints

    //ip weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/asset/tradeFee")
    Call<List<FeeStructure>> getFeeStructure(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //uid weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order")
    Call<NewOrderResponse> newOrder(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                                    @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity,
                                    @Query("quoteOrderQty") String quoteOrderQty, @Query("price") String price,
                                    @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                                    @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                    @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order/test")
    Call<Void> newOrderTest(@Query("symbol") String symbol, @Query("side") OrderSide side, @Query("type") OrderType type,
                            @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity,
                            @Query("quoteOrderQty") String quoteOrderQty, @Query("price") String price,
                            @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                            @Query("icebergQty") String icebergQty, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                            @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/order")
    Call<Order> getOrderStatus(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                               @Query("origClientOrderId") String origClientOrderId, @Query("recvWindow") Long recvWindow,
                               @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/api/v3/order")
    Call<CancelOrderResponse> cancelOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                          @Query("origClientOrderId") String origClientOrderId, @Query("newClientOrderId") String newClientOrderId,
                                          @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/openOrders")
    Call<List<Order>> getOpenOrders(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/allOrders")
    Call<List<Order>> getAllOrders(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                   @Query("startTime") Long startTime, @Query("endTime") Long endTime,
                                   @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/api/v3/order/oco")
    Call<NewOCOResponse> newOCO(@Query("symbol") String symbol, @Query("listClientOrderId") String listClientOrderId,
                                @Query("side") OrderSide side, @Query("quantity") String quantity, @Query("limitClientOrderId") String limitClientOrderId,
                                @Query("price") String price, @Query("limitIcebergQty") String limitIcebergQty, @Query("stopClientOrderId") String stopClientOrderId,
                                @Query("stopPrice") String stopPrice, @Query("stopLimitPrice") String stopLimitPrice, @Query("stopIcebergQty") String stopIcebergQty,
                                @Query("stopLimitTimeInForce") TimeInForce stopLimitTimeInForce, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                @Query("recvWindow") Long recvWindow, @Query("timestamp") long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/api/v3/orderList")
    Call<CancelOrderListResponse> cancelOrderList(@Query("symbol") String symbol, @Query("orderListId") Long orderListId, @Query("listClientOrderId") String listClientOrderId,
                                                  @Query("newClientOrderId") String newClientOrderId, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/orderList")
    Call<OrderList> getOrderListStatus(@Query("orderListId") Long orderListId, @Query("origClientOrderId") String origClientOrderId,
                                       @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/allOrderList")
    Call<List<OrderList>> getAllOrderList(@Query("fromId") Long fromId, @Query("startTime") Long startTime, @Query("endTime") Long endTime,
                                          @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 10
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/account")
    Call<Account> getAccount(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/api/v3/myTrades")
    Call<List<Trade>> getMyTrades(@Query("symbol") String symbol, @Query("limit") Integer limit, @Query("fromId") Long fromId,
                                  @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/asset/transfer")
    Call<Transaction> universalTransfer(@Query("type") UniversalTransferType type, @Query("asset") String asset, @Query("amount") String amount,
                                        @Query("fromSymbol") String fromSymbol, @Query("toSymbol") String toSymbol,
                                        @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/futures/transfer")
    Call<Transaction> transferToFutures(@Query("asset") String asset, @Query("amount") String amount, @Query("type") SpotTransferType type,
                                        @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/capital/withdraw/apply")
    Call<WithdrawResult> withdraw(@Query("coin") String asset, @Query("address") String address, @Query("network") String network, @Query("amount") String amount,
                                  @Query("name") String name, @Query("addressTag") String addressTag,
                                  @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/wapi/v3/depositHistory.html")
    Call<DepositHistory> getDepositHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/wapi/v3/withdrawHistory.html")
    Call<WithdrawHistory> getWithdrawHistory(@Query("asset") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/capital/deposit/address")
    Call<DepositAddress> getDefaultDepositAddress(@Query("coin") String asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/capital/deposit/address")
    Call<DepositAddress> getDepositAddress(@Query("coin") String asset, @Query("network") String network, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //uid weight: 10
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/sapi/v1/asset/dust")
    Call<DustTransferResponse> dustTransfer(@Query("asset") List<String> asset, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/sapi/v1/sub-account/transfer/subUserHistory")
    Call<List<SubAccountTransfer>> getSubAccountTransfers(@Query("timestamp") Long timestamp);

    // User stream endpoints

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/api/v3/userDataStream")
    Call<ListenKey> startUserDataStream();

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/api/v3/userDataStream")
    Call<Void> keepAliveUserDataStream(@Query("listenKey") String listenKey);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/api/v3/userDataStream")
    Call<Void> closeAliveUserDataStream(@Query("listenKey") String listenKey);

    // Binance Liquidity Swap Pool endpoints

    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @GET("/sapi/v1/bswap/pools")
    Call<List<Pool>> listAllSwapPools();

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/bswap/liquidity")
    Call<List<Liquidity>> getPoolLiquidityInfo(@Query("poolId") String poolId,
                                               @Query("recvWindow") Long recvWindow,
                                               @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/bswap/liquidityAdd")
    Call<LiquidityOperationRecord> addLiquidity(@Query("poolId") String poolId,
                                                @Query("asset") String asset,
                                                @Query("quantity") String quantity,
                                                @Query("recvWindow") Long recvWindow,
                                                @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/bswap/liquidityRemove")
    Call<LiquidityOperationRecord> removeLiquidity(@Query("poolId") String poolId,
                                                   @Query("type") SwapRemoveType type,
                                                   @Query("asset") List<String> asset,
                                                   @Query("shareAmount") String shareAmount,
                                                   @Query("recvWindow") Long recvWindow,
                                                   @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/bswap/liquidityOps")
    Call<List<LiquidityOperationRecord>> getPoolLiquidityOperationRecords(
            @Query("poolId") String poolId,
            @Query("limit") Integer limit,
            @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/bswap/liquidityOps")
    Call<List<LiquidityOperationRecord>> getLiquidityOperationRecord(
            @Query("operationId") String operationId,
            @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/bswap/quote")
    Call<SwapQuote> requestQuote(
            @Query("quoteAsset") String quoteAsset,
            @Query("baseAsset") String baseAsset,
            @Query("quoteQty") String quoteQty,
            @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @POST("/sapi/v1/bswap/swap")
    Call<SwapRecord> swap(
            @Query("quoteAsset") String quoteAsset,
            @Query("baseAsset") String baseAsset,
            @Query("quoteQty") String quoteQty,
            @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @Headers({BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER, BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER})
    @GET("/sapi/v1/bswap/swap")
    Call<List<SwapHistory>> getSwapHistory(
            @Query("swapId") String swapId,
            @Query("recvWindow") Long recvWindow,
            @Query("timestamp") Long timestamp);

    @GET("/sapi/v1/account/status")
    Call<AccountStatusResponse> getAccountStatus(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);
}
