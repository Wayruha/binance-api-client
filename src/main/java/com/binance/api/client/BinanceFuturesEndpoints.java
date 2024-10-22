package com.binance.api.client;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.TickerPrice;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BinanceFuturesEndpoints {
    //Web Sockets
    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("/fapi/v1/listenKey")
    Call<ListenKey> startUserDataStream();

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("/fapi/v1/listenKey")
    Call<Void> keepAliveUserDataStream(@Query("listenKey") String listenKey);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/fapi/v1/listenKey")
    Call<Void> closeAliveUserDataStream(@Query("listenKey") String listenKey);

    //api weight: 1
    @GET("/fapi/v1/exchangeInfo")
    Call<ExchangeInfo> getExchangeInfo();

    //Trade
    //api weight: 1
    @GET("/fapi/v1/ticker/price")
    Call<TickerPrice> getSymbolPrice(@Query("symbol") String symbol);

    //api weight: 2
    @GET("/fapi/v1/ticker/price")
    Call<List<TickerPrice>> getAllSymbolsPrice();

    //uid weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/order")
    Call<FuturesOrder> newOrder(@Query("symbol") String symbol, @Query("side") OrderSide side,
                                @Query("positionSide") PositionSide positionSide, @Query("type") OrderType type,
                                @Query("timeInForce") TimeInForce timeInForce, @Query("quantity") String quantity,
                                @Query("reduceOnly") Boolean reduceOnly, @Query("price") String price,
                                @Query("newClientOrderId") String newClientOrderId, @Query("stopPrice") String stopPrice,
                                @Query("closePosition") Boolean closePosition, @Query("activationPrice") String activationPrice,
                                @Query("callbackRate") String callbackRate, @Query("workingType") WorkingType workingType,
                                @Query("priceProtect") Boolean priceProtect, @Query("newOrderRespType") NewOrderResponseType newOrderRespType,
                                @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v1/order")
    Call<FuturesOrder> queryOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                  @Query("origClientOrderId") String origClientOrderId, @Query("recvWindow") Long recvWindow,
                                  @Query("timestamp") Long timestamp);

    //weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/fapi/v1/order")
    Call<FuturesOrder> cancelOrder(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                   @Query("origClientOrderId") String origClientOrderId,
                                   @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //weight: 1 for a single symbol; 40 when the symbol parameter is omitted
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v1/openOrders")
    Call<List<FuturesAllOpenOrdersResponse>> allOpenOrders(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("/fapi/v1/allOpenOrders")
    Call<StatusResponse> cancelAllOpenOrder(@Query("symbol") String symbol, @Query("recvWindow") Long recvWindow,
                                            @Query("timestamp") Long timestamp);

    /**
     * If orderId is set, it will get orders >= that orderId. Otherwise most recent orders are returned.
     * weight: 5
     */
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v1/allOrders")
    Call<List<FuturesOrder>> getAllOrders(@Query("symbol") String symbol, @Query("orderId") Long orderId,
                                          @Query("startTime") Long startTime, @Query("endTime") Long endTime,
                                          @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //Balance

    //weight: 5
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v2/balance")
    Call<List<FuturesAccountBalance>> accountBalance(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 5
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v2/account")
    Call<FuturesAccountInfo> accountInfo(@Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //Trade
    //api weight: 1, leverage in [1, 125]
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/leverage")
    Call<ChangeLeverageResponse> changeInitialLeverage(@Query("symbol") String symbol, @Query("leverage") int leverage,
                                                       @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/marginType")
    Call<StatusResponse> changeMarginType(@Query("symbol") String symbol, @Query("marginType") MarginType leverage,
                                          @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 1, only for isolated symbol
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/positionMargin")
    Call<StatusResponse> changePositionMargin(@Query("symbol") String symbol, @Query("positionSide") PositionSide positionSide,
                                              @Query("amount") String amount, @Query("type") MarginChangeType type,
                                              @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v2/positionRisk")
    Call<List<FuturesPosition>> positionInformation(@Query("symbol") String symbol,
                                                    @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //weight: 5
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v1/userTrades")
    Call<List<FuturesTradeHistoryItem>> userTrades(@Query("symbol") String symbol, @Query("startTime") Long startTime,
                                                   @Query("endTime") Long endTime, @Query("fromId") Long fromId,
                                                   @Query("limit") Integer limit,
                                                   @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 1
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @POST("/fapi/v1/positionSide/dual")
    Call<StatusResponse> setPositionSide(@Query("dualSidePosition") boolean dualSidePosition,
                                         @Query("recvWindow") Long recvWindow, @Query("timestamp") Long timestamp);

    //api weight: 30
    @Headers(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("/fapi/v1/income")
    Call<List<IncomeHistoryItem>> incomeHistory(@Query("symbol") String symbol, @Query("incomeType") FuturesIncomeType incomeType,
                                                @Query("startTime") Long startTime, @Query("endTime") Long endTime,
                                                @Query("limit") Integer limit, @Query("recvWindow") Long recvWindow,
                                                @Query("timestamp") Long timestamp);

}
