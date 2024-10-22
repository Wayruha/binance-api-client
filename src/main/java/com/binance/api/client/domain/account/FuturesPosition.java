package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.MarginType;
import com.binance.api.client.domain.PositionSide;
import com.binance.api.client.domain.account.request.FuturesAccountInfo;
import com.binance.api.client.domain.event.FuturesAccountUpdateEvent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

import static com.binance.api.client.domain.PositionSide.BOTH;
import static com.binance.api.client.domain.PositionSide.LONG;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FuturesPosition {
    private String symbol;
    private PositionSide positionSide;
    private String positionAmt;
    private String entryPrice;
    private Integer leverage;
    private Boolean isolated;
    private String maxNotional;  // maximum available notional with current leverage

    //everything below is used only in 'Position information' response
    private MarginType marginType;
    private Boolean isAutoAddMargin;
    private String isolatedMargin;
    private String liquidationPrice;
    private String markPrice;
    private String maxNotionalValue;
    private String unRealizedProfit;
    @JsonUnwrapped
    private FuturesAccountInfo.Margin margin;

    public FuturesPosition(FuturesAccountUpdateEvent.Position pos) {
        this.symbol = pos.getSymbol();
        this.positionSide = pos.getPositionSide();
        this.positionAmt = pos.getPositionAmount();
        this.entryPrice = pos.getEntryPrice();
        this.marginType = MarginType.fromShortName(pos.getMarginType());
        this.isolated = this.marginType == MarginType.ISOLATED;
    }

    public boolean isLongPosition() {
        return positionSide == LONG || (positionSide == BOTH && ofNullable(createBigDecimal(positionAmt)).orElse(BigDecimal.ZERO).signum() > 0);
    }

    @Override
    public String toString() {
        ToStringBuilder bldr = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE);
        if (symbol != null) bldr.append("symbol", symbol);
        if (positionSide != null) bldr.append("positionSide", positionSide);
        if (positionAmt != null) bldr.append("positionAmt", positionAmt);
        if (entryPrice != null) bldr.append("entryPrice", entryPrice);
        if (leverage != null) bldr.append("leverage", leverage);
        if (isolated != null) bldr.append("isolated", isolated);
        if (maxNotional != null) bldr.append("maxNotional", maxNotional);
        if (marginType != null) bldr.append("marginType", marginType);
        if (isAutoAddMargin != null) bldr.append("isAutoAddMargin", isAutoAddMargin);
        if (isolatedMargin != null) bldr.append("isolatedMargin", isolatedMargin);
        if (liquidationPrice != null) bldr.append("liquidationPrice", liquidationPrice);
        if (markPrice != null) bldr.append("markPrice", markPrice);
        if (maxNotionalValue != null) bldr.append("maxNotionalValue", maxNotionalValue);
        if (unRealizedProfit != null) bldr.append("unRealizedProfit", unRealizedProfit);
        if (margin != null) bldr.append("margin", margin);
        return bldr.toString();
    }
}
