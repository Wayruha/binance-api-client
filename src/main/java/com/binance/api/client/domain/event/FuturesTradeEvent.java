package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * Order or trade report update event for Binance Futures
 * This event is embedded as part of a user data update event.
 * Partial copy of UserDataUpdateEvent
 * !!! Currently not supported JSON parameters:
 * pP, si, ss
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuturesTradeEvent extends TradeEvent {

    @JsonProperty("ap")
    private String averagePrice;

    @JsonProperty("sp")
    private String stopPrice;

    @JsonProperty("b")
    private String bidsNotional;

    @JsonProperty("a")
    private String askNotional;

    @JsonProperty("m")
    private Boolean isMaker;

    @JsonProperty("R")
    private Boolean isReduceOnly;

    //Stop Price Working Type
    @JsonProperty("wt")
    private WorkingType stopPriceWorkingType;

    @JsonProperty("ot")
    private OrderType originalOrderType;

    @JsonProperty("ps")
    private PositionSide positionSide;

    // If Close-All, pushed with conditional order
    @JsonProperty("cp")
    private Boolean isCloseAll;

    //only with TRAILING_STOP_MARKET order
    @JsonProperty("AP")
    private String activationPrice;

    // Callback Rate, only pushed with TRAILING_STOP_MARKET order
    @JsonProperty("cr")
    private String callbackRate;

    // Realized Profit of the trade
    @JsonProperty("rp")
    private String realizedTradeProfit;

    public String getActualOrderId() {
        return StringUtils.isNotEmpty(newClientOrderId) ? newClientOrderId : orderId.toString();
    }

    /**
     * it uses 'priceOfLastFilledTrade' for calculation which may differ between partial fillings.
     * Therefore, the result is not exact.
     * Better not to use this method.
     */
    @Override
    public BigDecimal getQuoteQty() {
        return toBigDecimal(originalQuantity).multiply(toBigDecimal(priceOfLastFilledTrade));
    }

    public BigDecimal getLastPrice() {
        return toBigDecimal(priceOfLastFilledTrade);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("side", side)
                .append("type", type)
                .append("isReduceOnly", isReduceOnly)
                .append("executionType", executionType)
                .append("originalQuantity", originalQuantity)
                .append("price", price)
                .append("orderStatus", orderStatus)
                .append("quantityLastFilledTrade", quantityLastFilledTrade)
                .append("accumulatedQuantity", accumulatedQuantity)
                .append("priceOfLastFilledTrade", priceOfLastFilledTrade)
                .append("stopPrice", stopPrice)
                .append("stopPriceWorkingType", stopPriceWorkingType)
                .append("originalOrderType", originalOrderType)
                .append("realizedTradeProfit", realizedTradeProfit)
                .append("positionSide", positionSide)
                .append("isCloseAll", isCloseAll)
                .append("activationPrice", activationPrice)
                .append("orderId", orderId)
                .append("newClientOrderId", newClientOrderId)
                .append("eventTime", eventTime)
                .append("timeInForce", timeInForce)
                .append("tradeId", tradeId)
                .append("commission", commission)
                .append("commissionAsset", commissionAsset)
                .append("orderTradeTime", orderTradeTime)
                .append("bidsNotional", bidsNotional)
                .append("askNotional", askNotional)
                .append("isMaker", isMaker)
                .append("callbackRate", callbackRate)
                .append("eventType", eventType)
                .toString();
    }
}
