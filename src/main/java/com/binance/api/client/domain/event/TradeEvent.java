package com.binance.api.client.domain.event;

import com.binance.api.client.domain.*;
import com.binance.api.client.domain.account.OrderDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TradeEvent extends AbstractEvent implements OrderDetails {

    @JsonProperty("s")
    protected String symbol;
    @JsonProperty("c")
    protected String newClientOrderId;
    /**
     * Buy/Sell order side.
     */
    @JsonProperty("S")
    protected OrderSide side;
    /**
     * Type of order.
     */
    @JsonProperty("o")
    protected OrderType type;
    /**
     * Time in force to indicate how long will the order remain active.
     */
    @JsonProperty("f")
    protected TimeInForce timeInForce;
    /**
     * Original quantity in the order.
     */
    @JsonProperty("q")
    protected String originalQuantity;
    /**
     * Price.
     */
    @JsonProperty("p")
    protected String price;
    /**
     * Type of execution.
     */
    @JsonProperty("x")
    protected ExecutionType executionType;
    /**
     * Status of the order.
     */
    @JsonProperty("X")
    protected OrderStatus orderStatus;
    /**
     * Order id.
     */
    @JsonProperty("i")
    protected Long orderId;
    /**
     * Quantity of the last filled trade.
     */
    @JsonProperty("l")
    protected String quantityLastFilledTrade;
    /**
     * Accumulated quantity of filled trades on this order.
     */
    @JsonProperty("z")
    protected String accumulatedQuantity;
    /**
     * Price of last filled trade.
     */
    @JsonProperty("L")
    protected String priceOfLastFilledTrade;
    /**
     * Asset on which commission is taken
     */
    @JsonProperty("N")
    protected String commissionAsset;
    /**
     * Commission.
     */
    @JsonProperty("n")
    protected String commission;
    /**
     * Order/trade time.
     */
    @JsonProperty("T")
    protected Long orderTradeTime;
    /**
     * Trade id.
     */
    @JsonProperty("t")
    protected Long tradeId;

    /**
    * different subclasses have different sets of existing (non-empty) parameters
    */
//    @Override
    public abstract BigDecimal getQuoteQty();

    public TradeEvent() {
    }

    public TradeEvent(AbstractEvent parentEvent) {
        super(parentEvent);
    }
}
