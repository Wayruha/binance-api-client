package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.PositionSide;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class MarginCallEvent extends AbstractEvent {
    public MarginCallEvent() {
        setEventType(EventType.MARGIN_CALL);
    }

    @JsonProperty("cw")
    private String crossWalletBalance;

    @JsonProperty("p")
    private List<Position> positions;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("transaction", transaction)
                .append("positions", positions)
                .append("crossWalletBalance", crossWalletBalance)
                .toString();
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @EqualsAndHashCode
    public static class Position {

        @JsonProperty("s")
        private String symbol;

        @JsonProperty("ps")
        private PositionSide positionSide;

        @JsonProperty("pa")
        private String positionAmount;

        @JsonProperty("mt")
        private String marginType;

        // Isolated Wallet (if isolated position)
        @JsonProperty("iw")
        private String isolatedWallet;

        @JsonProperty("mp")
        private String markPrice;

        @JsonProperty("up")
        private String unrealizedPnL;

        @JsonProperty("mm")
        private String requiredMaintenanceMargin;

        @Override
        public String toString() {
            return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                    .append("symbol", symbol)
                    .append("positionSide", positionSide)
                    .append("positionAmount", positionAmount)
                    .append("unrealizedPnL", unrealizedPnL)
                    .append("markPrice", markPrice)
                    .append("requiredMaintenanceMargin", requiredMaintenanceMargin)
                    .append("marginType", marginType)
                    .append("isolatedWallet", isolatedWallet)
                    .toString();
        }
    }
}
