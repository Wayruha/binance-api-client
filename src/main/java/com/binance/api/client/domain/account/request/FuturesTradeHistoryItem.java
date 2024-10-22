package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.PositionSide;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuturesTradeHistoryItem {
    private Boolean buyer;
    private BigDecimal commission;
    private String commissionAsset;
    private Long id;
    private Boolean maker;
    private Long orderId;
    private BigDecimal price;
    private BigDecimal qty;
    private BigDecimal quoteQty;
    private BigDecimal realizedPnl;
    private OrderSide side;
    private PositionSide positionSide;
    private String symbol;
    private Long time;

    @Override
    public String toString() {
        ToStringBuilder bldr = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE);
        if (symbol != null) bldr.append("symbol", symbol);
        if (realizedPnl != null) bldr.append("realizedPnl", realizedPnl);
        if (side != null) bldr.append("side", side);
        if (positionSide != null) bldr.append("positionSide", positionSide);
        if (qty != null) bldr.append("qty", qty);
        if (quoteQty != null) bldr.append("quoteQty", quoteQty);
        if (price != null) bldr.append("price", price);
        if (time != null) bldr.append("time", time);
        if (orderId != null) bldr.append("orderId", orderId);
        if (buyer != null) bldr.append("buyer", buyer);
        if (commission != null) bldr.append("commission", commission);
        if (commissionAsset != null) bldr.append("commissionAsset", commissionAsset);
        if (maker != null) bldr.append("maker", maker);
        if (id != null) bldr.append("id", id);
        return bldr.toString();
    }
}
