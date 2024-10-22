/*
package com.binance.api.client.domain.account;

import com.binance.api.client.domain.*;
import lombok.Data;

@Data
public class FuturesNewOrder implements OrderDetails {
    */
/**
     * Symbol to place the order on.
     *//*

    private String symbol;

    */
/**
     * Buy/Sell order side.
     *//*

    private OrderSide side;

    private PositionSide positionSide;

    */
/**
     * Type of order.
     *//*

    private OrderType type;

    private OrderStatus status;

    */
/**
     * Time in force to indicate how long will the order remain active.
     *//*

    private TimeInForce timeInForce;

    */
/**
     * Quantity.
     *//*

    private String quantity;

    private boolean reduceOnly;

    */
/**
     * Price.
     *//*

    private String price;

    */
/**
     * A unique id for the order. Automatically generated if not sent.
     *//*

    private String newClientOrderId;

    private Long orderId;

    */
/**
     * Used with stop orders.
     *//*

    private String stopPrice;

    private boolean closePosition;

    private String activationPrice;

    */
/**
     * Decimal: Used with TRAILING_STOP_MARKET orders, min 0.1, max 5 where 1 for 1%
     *//*

    private String callbackRate;

    */
/**
     * stopPrice triggered by: "MARK_PRICE", "CONTRACT_PRICE". Default "CONTRACT_PRICE"
     *//*

    private WorkingType workingType;

    */
/**
     * Used with STOP/STOP_MARKET or TAKE_PROFIT/TAKE_PROFIT_MARKET order. Default: false
     *//*

    private boolean priceProtect;

    */
/**
     * Set the response JSON. ACK, RESULT, or FULL; default: RESULT.
     *//*

    private NewOrderResponseType newOrderRespType;

    */
/**
     * Receiving window.
     *//*

    private Long recvWindow;

    */
/**
     * Order timestamp.
     *//*

    private long timestamp;

    @Override
    public String getOriginalQuantity() {
        return quantity;
    }

    @Override
    public ExecutionType getExecutionType() {
        return null;
    }

    @Override
    public Long getOrderTradeTime() {
        return null;
    }

    @Override
    public Long getTradeId() {
        return null;
    }

    @Override
    public String getActualOrderId() {
        return newClientOrderId;
    }
}
*/
