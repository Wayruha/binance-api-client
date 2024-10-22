package com.binance.api.client.domain.account.margin;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponseType;
import com.binance.api.client.domain.account.SideEffectType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A trade order to enter or exit a position.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginNewOrder {

    /**
     * Symbol to place the order on.
     */
    private String symbol;

    /**
     * Cross/Isolated margin to use(cross if not passed).
     */
    private Boolean isIsolated;

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
     * Used with iceberg orders.
     */
    private String icebergQty;

    /**
     * Set the response JSON. ACK, RESULT, or FULL; default: RESULT.
     */
    private NewOrderResponseType newOrderRespType;

    /**
     * Set the margin order side-effect. NO_SIDE_EFFECT, MARGIN_BUY, AUTO_REPAY; default: NO_SIDE_EFFECT.
     */
    private SideEffectType sideEffectType;

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
    public MarginNewOrder(String symbol, boolean isIsolated, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity, String quoteQty, String price) {
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.timeInForce = timeInForce;
        this.quantity = quantity;
        this.quoteOrderQty = quoteQty;
        this.price = price;
        this.isIsolated = isIsolated;
        this.newOrderRespType = NewOrderResponseType.RESULT;
        this.timestamp = System.currentTimeMillis();
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
    }

    public MarginNewOrder(String symbol, boolean isIsolated, OrderSide side, OrderType type, TimeInForce timeInForce, String quantity, String quoteQty) {
        this(symbol, isIsolated, side, type, timeInForce, quantity, quoteQty, null);
    }

    public String getSymbol() {
        return symbol;
    }

    public MarginNewOrder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public boolean isIsolated() {
        return isIsolated;
    }

    public MarginNewOrder isIsolated(boolean isIsolated) {
        this.isIsolated = isIsolated;
        return this;
    }

    public OrderSide getSide() {
        return side;
    }

    public MarginNewOrder side(OrderSide side) {
        this.side = side;

        return this;
    }

    public OrderType getType() {
        return type;
    }

    public MarginNewOrder type(OrderType type) {
        this.type = type;
        return this;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public MarginNewOrder timeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public MarginNewOrder quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getQuoteOrderQty() {
        return quoteOrderQty;
    }

    public MarginNewOrder quoteOrderQty(String quoteOrderQty) {
        this.quoteOrderQty = quoteOrderQty;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public MarginNewOrder price(String price) {
        this.price = price;
        return this;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public MarginNewOrder newClientOrderId(String newClientOrderId) {
        this.newClientOrderId = newClientOrderId;
        return this;
    }

    public String getStopPrice() {
        return stopPrice;
    }

    public MarginNewOrder stopPrice(String stopPrice) {
        this.stopPrice = stopPrice;
        return this;
    }

    public String getIcebergQty() {
        return icebergQty;
    }

    public MarginNewOrder icebergQty(String icebergQty) {
        this.icebergQty = icebergQty;
        return this;
    }

    public NewOrderResponseType getNewOrderRespType() {
        return newOrderRespType;
    }

    public MarginNewOrder newOrderRespType(NewOrderResponseType newOrderRespType) {
        this.newOrderRespType = newOrderRespType;
        return this;
    }

    public SideEffectType getSideEffectType() {
        return sideEffectType;
    }

    public MarginNewOrder sideEffectType(SideEffectType sideEffectType) {
        this.sideEffectType = sideEffectType;
        return this;
    }

    public Long getRecvWindow() {
        return recvWindow;
    }

    public MarginNewOrder recvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MarginNewOrder timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public static MarginNewOrder from(NewOrder order, boolean isIsolated) {
        return new MarginNewOrder(order.getSymbol(), isIsolated, order.getSide(), order.getType(), order.getTimeInForce(), order.getQuantity(), order.getQuoteOrderQty(), order.getPrice());
    }

    /**
     * Places a MARKET buy order for the given <code>quantity</code>.
     *
     * @return a new order which is pre-configured with MARKET as the order type and BUY as the order side.
     */
    public static MarginNewOrder marketBuy(String symbol, boolean isIsolated, String quantity, String quoteQty) {
        return new MarginNewOrder(symbol, isIsolated, OrderSide.BUY, OrderType.MARKET, null, quantity, quoteQty);
    }

    /**
     * Places a MARKET sell order for the given <code>quantity</code>.
     *
     * @return a new order which is pre-configured with MARKET as the order type and SELL as the order side.
     */
    public static MarginNewOrder marketSell(String symbol, boolean isIsolated, String quantity, String quoteQty) {
        return new MarginNewOrder(symbol, isIsolated, OrderSide.SELL, OrderType.MARKET, null, quantity, quoteQty);
    }

    /**
     * Places a LIMIT buy order for the given <code>quantity</code> and <code>price</code>.
     *
     * @return a new order which is pre-configured with LIMIT as the order type and BUY as the order side.
     */
    public static MarginNewOrder limitBuy(String symbol, boolean isIsolated, TimeInForce timeInForce, String quantity, String quoteQty, String price) {
        return new MarginNewOrder(symbol, isIsolated, OrderSide.BUY, OrderType.LIMIT, timeInForce, quantity, quoteQty, price);
    }

    /**
     * Places a LIMIT sell order for the given <code>quantity</code> and <code>price</code>.
     *
     * @return a new order which is pre-configured with LIMIT as the order type and SELL as the order side.
     */
    public static MarginNewOrder limitSell(String symbol, boolean isIsolated, TimeInForce timeInForce, String quantity, String quoteQty, String price) {
        return new MarginNewOrder(symbol, isIsolated, OrderSide.SELL, OrderType.LIMIT, timeInForce, quantity, quoteQty, price);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("isIsolated", isIsolated)
                .append("side", side)
                .append("type", type)
                .append("timeInForce", timeInForce)
                .append("quantity", quantity)
                .append("quoteOrderQty", quoteOrderQty)
                .append("price", price)
                .append("newClientOrderId", newClientOrderId)
                .append("stopPrice", stopPrice)
                .append("icebergQty", icebergQty)
                .append("newOrderRespType", newOrderRespType)
                .append("sideEffectType", sideEffectType)
                .append("recvWindow", recvWindow)
                .append("timestamp", timestamp)
                .toString();
    }
}
