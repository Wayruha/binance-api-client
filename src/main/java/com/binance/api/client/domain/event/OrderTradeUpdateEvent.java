package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderRejectReason;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * Order or trade report update event.
 * <p>
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class OrderTradeUpdateEvent extends TradeEvent {

    @JsonProperty("P")
    private String stopPrice;

    /**
     * Reason why the order was rejected.
     */
    @JsonProperty("r")
    private OrderRejectReason orderRejectReason;

    /**
     * Order creation time.
     */
    @JsonProperty("O")
    private Long orderCreationTime;

    /**
     * Cumulative quote asset transacted quantity.
     */
    @JsonProperty("Z")
    private String cumulativeQuoteQty;

    /**
     * Last quote asset transacted quantity (i.e. lastPrice * lastQty).
     */
    @JsonProperty("Y")
    private String lastQuoteQty;

    /**
     * Quote Order Qty.
     */
    @JsonProperty("Q")
    private String quoteOrderQty;

    @JsonProperty("d")
    private String trailingDelta;


    @Override
    public BigDecimal getQuoteQty() {
        BigDecimal quoteQty = StringUtils.isNotEmpty(quoteOrderQty) ? new BigDecimal(quoteOrderQty) : null;
        if (quoteQty == null || quoteQty.signum() == 0) {
            final BigDecimal qty = toBigDecimal(originalQuantity);
            final BigDecimal price = getLastPrice();
            return qty.multiply(price);
        } else {
            return quoteQty;
        }
    }

    public BigDecimal getLastPrice() {
        return toBigDecimal(priceOfLastFilledTrade);
    }

    @Override
    public String getActualOrderId() {
        return StringUtils.isNotEmpty(getNewClientOrderId()) ? getNewClientOrderId() : getOrderId().toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("symbol", symbol)
                .append("side", side)
                .append("type", type)
                .append("orderStatus", orderStatus)
                .append("executionType", executionType)
                .append("price", price)
                .append("originalQuantity", originalQuantity)
                .append("cumulativeQuoteQty", cumulativeQuoteQty)
                .append("lastQuoteQty", lastQuoteQty)
                .append("quoteOrderQty", quoteOrderQty)
                .append("newClientOrderId", newClientOrderId)
                .append("orderId", orderId)
                .append("timeInForce", timeInForce)
                .append("orderRejectReason", orderRejectReason)
                .append("quantityLastFilledTrade", quantityLastFilledTrade)
                .append("accumulatedQuantity", accumulatedQuantity)
                .append("priceOfLastFilledTrade", priceOfLastFilledTrade)
                .append("commission", commission)
                .append("commissionAsset", commissionAsset)
                .append("orderTradeTime", orderTradeTime)
                .append("tradeId", tradeId)
                .append("orderCreationTime", orderCreationTime)
                .append("eventType", eventType)
                .toString();
    }
}
