package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.binance.api.client.domain.event.TradeEvent;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class FuturesOrder implements OrderDetails {

    protected String symbol;

    protected OrderType type;

    @Getter(AccessLevel.NONE)
    protected OrderStatus status;

    protected OrderSide side;

    protected Boolean reduceOnly;

    protected String price;

    @Getter(AccessLevel.NONE)
    protected String origQty;

    protected String executedQty;

    protected String cumQuote;

    protected String cumQty;

    @Getter(AccessLevel.NONE)
    protected String clientOrderId;

    protected Long orderId;

    protected TimeInForce timeInForce;

    protected OrderType origType;

    protected PositionSide positionSide;

    // callback rate, only return with TRAILING_STOP_MARKET order
    protected String priceRate;

    protected String avgPrice;

    // please ignore when order type is TRAILING_STOP_MARKET
    protected String stopPrice;

    protected Boolean closePosition;

    @JsonAlias({"time", "timestamp"})
    protected Long time;

    protected Long updateTime;

    protected WorkingType workingType;

    // activation price, only return with TRAILING_STOP_MARKET order
    protected String activatePrice;

    protected Boolean priceProtect;

    public FuturesOrder(String symbol, OrderSide side, OrderType type, String quantity) {
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.origQty = quantity;
    }

    public static FuturesOrder marketOrderFromEvent(String symbol,
                                                    OrderSide side,
                                                    PositionSide positionSide,
                                                    String quantity,
                                                    Boolean reduceOnly,
                                                    String newClientOrderId) {
        final FuturesOrder order = new FuturesOrder(symbol, side, OrderType.MARKET, quantity);
        order.setPositionSide(positionSide);
        order.setReduceOnly(reduceOnly);
        order.setClientOrderId(newClientOrderId);
        return order;
    }

    public static FuturesOrder marketOrderFromEvent(TradeEvent event, String quantity) {
        return new FuturesOrder(event.getSymbol(), event.getSide(), OrderType.MARKET, quantity);
    }

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
        return clientOrderId == null ? orderId.toString() : clientOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("type", type)
                .append("status", status)
                .append("side", side)
                .append("reduceOnly", reduceOnly)
                .append("price", price)
                .append("origQty", origQty)
                .append("executedQty", executedQty)
                .append("cumQuote", cumQuote)
                .append("cumQty", cumQty)
                .append("clientOrderId", clientOrderId)
                .append("orderId", orderId)
                .append("timeInForce", timeInForce)
                .append("origType", origType)
                .append("positionSide", positionSide)
                .append("priceRate", priceRate)
                .append("avgPrice", avgPrice)
                .append("stopPrice", stopPrice)
                .append("closePosition", closePosition)
                .append("time", time)
                .append("updateTime", updateTime)
                .append("workingType", workingType)
                .append("activatePrice", activatePrice)
                .append("priceProtect", priceProtect)
                .toString();
    }
}
