package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderStatus;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A trade order to enter or exit a position.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOrder implements OrderDetails {

    /**
     * Symbol to place the order on.
     */
    private String symbol;

    /**
     * Buy/Sell order side.
     */
    private OrderSide side;

    /**
     * Type of order.
     */
    private OrderType type;

    /**
     * Time in force to indicate how long will the order remain active.
     */
    private TimeInForce timeInForce;

    /**
     * Quantity.
     */
    private String quantity;

    /**
     * Quote quantity.
     */
    private String quoteOrderQty;

    /**
     * Price.
     */
    private String price;

    /**
     * A unique id for the order. Automatically generated if not sent.
     */
    private String newClientOrderId;

    /**
     * Used with stop orders.
     */
    private String stopPrice;

    /**
     * Used with stop orders.
     */
    private String stopLimitPrice;

    /**
     * Used with iceberg orders.
     */
    private String icebergQty;

    /**
     * Set the response JSON. ACK, RESULT, or FULL; default: RESULT.
     */
    private NewOrderResponseType newOrderRespType;

    /**
     * Receiving window.
     */
    private Long recvWindow;

    /**
     * Order timestamp.
     */
    private long timestamp;

    /**
     * Creates a new order with all required parameters.
     */
    public NewOrder(String symbol, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity, String quoteOrderQty, String newClientOrderId) {
        if (quantity != null && quoteOrderQty != null)
            throw new IllegalArgumentException("Can't create order with both quantity and quoteOrderQty specified! Only 1 is allowed");
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.timeInForce = timeInForce;
        this.quantity = quantity;
        this.quoteOrderQty = quoteOrderQty;
        this.newOrderRespType = NewOrderResponseType.RESULT;
        this.newClientOrderId = newClientOrderId;
        this.timestamp = System.currentTimeMillis();
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
    }

    /**
     * Creates a new order with all required parameters plus price, which is optional for MARKET orders.
     */
    public NewOrder(String symbol, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity, String quoteOrderQty, String newClientOrderId, String price) {
        this(symbol, side, type, timeInForce, quantity, quoteOrderQty, newClientOrderId);
        this.price = price;
    }

    /**
     * Places a MARKET buy order for the given <code>quantity</code>.
     *
     * @return a new order which is pre-configured with MARKET as the order type and BUY as the order side.
     */
    public static NewOrder marketBuy(String symbol, String quantity) {
        return marketBuy(symbol, quantity, null);
    }

    public static NewOrder marketBuy(String symbol, String quantity, String newClientOrderId) {
        return new NewOrder(symbol, OrderSide.BUY, OrderType.MARKET, null, quantity, null, newClientOrderId);
    }

    public static NewOrder marketBuyQuoteQty(String symbol, String quoteQty) {
        return marketBuyQuoteQty(symbol, quoteQty, null);
    }

    public static NewOrder marketBuyQuoteQty(String symbol, String quoteQty, String newClientOrderId) {
        return new NewOrder(symbol, OrderSide.BUY, OrderType.MARKET, null, null, quoteQty, newClientOrderId);
    }

    /**
     * Places a MARKET sell order for the given <code>quantity</code>.
     *
     * @return a new order which is pre-configured with MARKET as the order type and SELL as the order side.
     */
    public static NewOrder marketSell(String symbol, String quantity) {
        return marketSell(symbol, quantity, null);
    }

    public static NewOrder marketSell(String symbol, String quantity, String newClientOrderId) {
        return new NewOrder(symbol, OrderSide.SELL, OrderType.MARKET, null, quantity, null, newClientOrderId);
    }

    public static NewOrder marketSellQuoteQty(String symbol, String quoteQty) {
        return marketSellQuoteQty(symbol, quoteQty, null);
    }

    public static NewOrder marketSellQuoteQty(String symbol, String quoteQty, String newClientOrderId) {
        return new NewOrder(symbol, OrderSide.SELL, OrderType.MARKET, null, null, quoteQty, newClientOrderId);
    }

    /**
     * Places a LIMIT buy order for the given <code>quantity</code> and <code>price</code>.
     *
     * @return a new order which is pre-configured with LIMIT as the order type and BUY as the order side.
     */
    public static NewOrder limitBuy(String symbol, TimeInForce timeInForce, String quantity, String quoteQty, String price) {
        return new NewOrder(symbol, OrderSide.BUY, OrderType.LIMIT, timeInForce, quantity, quoteQty, null, price);
    }

    /**
     * Places a LIMIT sell order for the given <code>quantity</code> and <code>price</code>.
     *
     * @return a new order which is pre-configured with LIMIT as the order type and SELL as the order side.
     */
    public static NewOrder limitSell(String symbol, TimeInForce timeInForce, String quantity, String quoteQty, String price) {
        return new NewOrder(symbol, OrderSide.SELL, OrderType.LIMIT, timeInForce, quantity, quoteQty, null, price);
    }

    @Override
    public String getOriginalQuantity() {
        return quantity;
    }

    @Override
    public OrderStatus getOrderStatus() {
        throw new NotImplementedException("getOrderStatus is not available in the NewOrder.class");
    }

    @Override
    public Long getOrderId() {
        throw new NotImplementedException("getOrderId is not available in the NewOrder.class");
    }

    @Override
    public String getActualOrderId() {
        return newClientOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("side", side)
                .append("type", type)
                .append("timeInForce", timeInForce)
                .append("quantity", quantity)
                .append("quoteOrderQty", quoteOrderQty)
                .append("price", price)
                .append("newClientOrderId", newClientOrderId)
                .append("stopPrice", stopPrice)
                .append("stopLimitPrice", stopLimitPrice)
                .append("icebergQty", icebergQty)
                .append("newOrderRespType", newOrderRespType)
                .append("recvWindow", recvWindow)
                .append("timestamp", timestamp)
                .toString();
    }
}
