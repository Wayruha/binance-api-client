package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

import static com.binance.api.client.domain.PositionSide.*;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.math.NumberUtils.createBigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuturesAccountUpdateEvent extends AbstractEvent {
    // Event reason type
    @JsonProperty("m")
    private AccountUpdateReasonType reasonType;

    //balances
    @JsonProperty("B")
    private List<Asset> balances;

    @JsonProperty("P")
    private List<Position> positions;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("reasonType", reasonType)
                .append("balances", balances)
                .append("positions", positions)
                .toString();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Asset {

        @JsonProperty("a")
        private String asset;

        @JsonProperty("wb")
        private String walletBalance;

        @JsonProperty("cw")
        private String crossWalletBalance;

        @JsonProperty("bc")
        private String balanceChange;

        @Override
        public String toString() {
            return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                    .append("asset", asset)
                    .append("balanceChange", balanceChange)
                    .append("walletBalance", walletBalance)
                    .append("crossWalletBalance", crossWalletBalance)
                    .toString();
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Position {
        @JsonProperty("s")
        private String symbol;

        @JsonProperty("pa")
        private String positionAmount;

        @JsonProperty("ep")
        private String entryPrice;

        //(Pre-fee) Accumulated Realized
        @JsonProperty("cr")
        private String accumulatedRealized;

        @JsonProperty("up")
        private String unrealizedPnL;

        @JsonProperty("mt")
        private String marginType;

        // Isolated Wallet (if isolated position)
        @JsonProperty("iw")
        private String isolatedWallet;

        @JsonProperty("ps")
        private PositionSide positionSide;

        public boolean isLongPosition() {
            return positionSide == LONG || (positionSide == BOTH && ofNullable(createBigDecimal(positionAmount)).orElse(BigDecimal.ZERO).signum() > 0);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                    .append("symbol", symbol)
                    .append("positionSide", positionSide)
                    .append("positionAmount", positionAmount)
                    .append("entryPrice", entryPrice)
                    .append("accumulatedRealized", accumulatedRealized)
                    .append("unrealizedPnL", unrealizedPnL)
                    .append("marginType", marginType)
                    .append("isolatedWallet", isolatedWallet)
                    .toString();
        }
    }
}
