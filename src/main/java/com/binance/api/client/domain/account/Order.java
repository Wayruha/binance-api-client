package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.event.TradeEvent;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Trade order information.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Order implements OrderDetails {

    /**
     * Symbol that the order was put on.
     */
    @JsonAlias({"s", "symbol"})
    private String symbol;

    /**
     * Order id.
     */
    @JsonAlias({"i", "orderId"})
    private Long orderId;

    /**
     * Client order id.
     */
    @Getter(AccessLevel.NONE)
    @JsonAlias({"c", "clientOrderId"})
    private String clientOrderId;

    /**
     * Price.
     */
    private String price;

    /**
     * Original quantity.
     */
    @Getter(AccessLevel.NONE)
    private String origQty;

    /**
     * Original quantity.
     */
    private String executedQty;

    /**
     * Order status.
     */
    @Getter(AccessLevel.NONE)
    private OrderStatus status;

    /**
     * Time in force to indicate how long will the order remain active.
     */
    private TimeInForce timeInForce;

    /**
     * Type of order.
     */
    private OrderType type;

    /**
     * Buy/Sell order side.
     */
    private OrderSide side;

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
     * Order timestamp.
     */
    private long time;

    /**
     * Used to calculate the average price
     */
    private String cummulativeQuoteQty;

    /**
     * Update timestamp.
     */
    private long updateTime;

    /**
     * Is working.
     */
    @JsonProperty("isWorking")
    private boolean working;

    /**
     * Original quote order quantity.
     */
    private String origQuoteOrderQty;

    private List<FillTx> fills;

    @Override
    public String getNewClientOrderId() {
        return clientOrderId;
    }

    @Override
    public String getOriginalQuantity() {
        return origQty;
    }

    @Override
    public OrderStatus getOrderStatus() {
        return status;
    }

    @Override
    public String getActualOrderId() {
        return clientOrderId == null ? String.valueOf(orderId) : clientOrderId;
    }

    public Order(TradeEvent orderEvent) {
        this.symbol = orderEvent.getSymbol();
        this.orderId = orderEvent.getOrderId();
        this.clientOrderId = orderEvent.getNewClientOrderId();
        this.price = orderEvent.getPrice();
        this.origQty = orderEvent.getOriginalQuantity();
        this.executedQty = orderEvent.getAccumulatedQuantity();
        this.status = orderEvent.getOrderStatus();
        this.timeInForce = orderEvent.getTimeInForce();
        this.type = orderEvent.getType();
        this.side = orderEvent.getSide();
        this.time = orderEvent.getEventTime() != null ? orderEvent.getEventTime() : 0L;
        this.side = orderEvent.getSide();
        this.updateTime = orderEvent.getOrderTradeTime() != null ? orderEvent.getOrderTradeTime() : 0L;
    }

    public Order(NewOrderResponse order) {
        this.symbol = order.getSymbol();
        this.side = order.getSide();
        this.type = order.getType();
        this.timeInForce = order.getTimeInForce();
        this.origQty = order.getOriginalQuantity();
        this.price = order.getPrice();
        this.clientOrderId = order.getNewClientOrderId();
        this.status = order.getOrderStatus();
        this.orderId = order.getOrderId();

        this.executedQty = order.getExecutedQty();
        this.cummulativeQuoteQty = order.getCummulativeQuoteQty();
        this.updateTime = order.getTransactTime();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FillTx {
        private String price;
        private String qty;
        private String commission;
        private String commissionAsset;
        private long tradeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("orderId", orderId)
                .append("clientOrderId", clientOrderId)
                .append("price", price)
                .append("origQty", origQty)
                .append("executedQty", executedQty)
                .append("status", status)
                .append("timeInForce", timeInForce)
                .append("type", type)
                .append("side", side)
                .append("stopPrice", stopPrice)
                .append("stopLimitPrice", stopLimitPrice)
                .append("icebergQty", icebergQty)
                .append("time", time)
                .append("cummulativeQuoteQty", cummulativeQuoteQty)
                .append("updateTime", updateTime)
                .append("isWorking", working)
                .append("origQuoteOrderQty", origQuoteOrderQty)
                .toString();
    }

}
