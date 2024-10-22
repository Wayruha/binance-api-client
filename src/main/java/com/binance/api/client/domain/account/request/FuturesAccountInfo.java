package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.MarginType;
import com.binance.api.client.domain.PositionSide;
import com.binance.api.client.domain.account.FuturesPosition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FuturesAccountInfo {
    private BigDecimal availableBalance;
    private BigDecimal maxWithdrawAmount;
    private BigDecimal totalWalletBalance;
    private BigDecimal totalCrossWalletBalance;
    private Long updateTime;
    private BigDecimal totalOpenOrderInitialMargin;
    private BigDecimal totalInitialMargin;
    private BigDecimal totalMaintMargin;
    private BigDecimal totalUnrealizedProfit;
    private BigDecimal totalMarginBalance;
    private BigDecimal totalPositionInitialMargin;
    private BigDecimal totalCrossUnPnl;
    private Integer feeTier;
    private Boolean canTrade;
    private Boolean canDeposit;
    private Boolean canWithdraw;
    private List<AssetInfo> assets;
    private List<FuturesPosition> positions;

    @Override
    public String toString() {
        ToStringBuilder bldr = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE);
        if (availableBalance != null) bldr.append("availableBalance", availableBalance.toPlainString());
        if (maxWithdrawAmount != null) bldr.append("maxWithdrawAmount", maxWithdrawAmount.toPlainString());
        if (totalWalletBalance != null) bldr.append("totalWalletBalance", totalWalletBalance.toPlainString());
        if (totalCrossWalletBalance != null) bldr.append("totalCrossWalletBalance", totalCrossWalletBalance.toPlainString());
        if (updateTime != null) bldr.append("updateTime", updateTime);
        if (totalOpenOrderInitialMargin != null)
            bldr.append("totalOpenOrderInitialMargin", totalOpenOrderInitialMargin.toPlainString());
        if (totalInitialMargin != null) bldr.append("totalInitialMargin", totalInitialMargin.toPlainString());
        if (totalMaintMargin != null) bldr.append("totalMaintMargin", totalMaintMargin.toPlainString());
        if (totalUnrealizedProfit != null) bldr.append("totalUnrealizedProfit", totalUnrealizedProfit.toPlainString());
        if (totalMarginBalance != null) bldr.append("totalMarginBalance", totalMarginBalance.toPlainString());
        if (totalPositionInitialMargin != null) bldr.append("totalPositionInitialMargin", totalPositionInitialMargin.toPlainString());
        if (totalCrossUnPnl != null) bldr.append("totalCrossUnPnl", totalCrossUnPnl.toPlainString());
        if (feeTier != null) bldr.append("feeTier", feeTier);
        if (canTrade != null) bldr.append("canTrade", canTrade);
        if (canDeposit != null) bldr.append("canDeposit", canDeposit);
        if (canWithdraw != null) bldr.append("canWithdraw", canWithdraw);
        if (assets != null) bldr.append("assets", assets);
        if (positions != null) bldr.append("positions", positions);
        return bldr.toString();
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AssetInfo {
        private String asset;
        private BigDecimal walletBalance;
        private BigDecimal availableBalance;
        private BigDecimal crossWalletBalance;
        private BigDecimal maxWithdrawAmount;
        private BigDecimal marginBalance;
        private BigDecimal crossUnPnl; // unrealized profit of crossed positions
        @JsonUnwrapped
        private Margin margin;

        @Override
        public String toString() {
            ToStringBuilder bldr = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE);
            if (asset != null) bldr.append("asset", asset);
            if (walletBalance != null) bldr.append("walletBalance", walletBalance.toPlainString());
            if (availableBalance != null) bldr.append("availableBalance", availableBalance.toPlainString());
            if (crossWalletBalance != null) bldr.append("crossWalletBalance", crossWalletBalance.toPlainString());
            if (maxWithdrawAmount != null) bldr.append("maxWithdrawAmount", maxWithdrawAmount.toPlainString());
            if (marginBalance != null) bldr.append("marginBalance", marginBalance.toPlainString());
            if (crossUnPnl != null) bldr.append("crossUnPnl", crossUnPnl.toPlainString());
            if (margin != null) bldr.append("margin", margin);
            return bldr.toString();
        }
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Margin {
        private BigDecimal maintMargin;
        private BigDecimal initialMargin; // total initial margin required with current mark price
        private BigDecimal positionInitialMargin; //initial margin required for positions with current mark price
        private BigDecimal openOrderInitialMargin; // initial margin required for open orders with current mark price
        private BigDecimal unrealizedProfit;

        @Override
        public String toString() {
            ToStringBuilder bldr = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE);
            if (maintMargin != null) bldr.append("maintMargin", maintMargin.toPlainString());
            if (initialMargin != null) bldr.append("initialMargin", initialMargin.toPlainString());
            if (positionInitialMargin != null) bldr.append("positionInitialMargin", positionInitialMargin.toPlainString());
            if (openOrderInitialMargin != null) bldr.append("openOrderInitialMargin", openOrderInitialMargin.toPlainString());
            if (unrealizedProfit != null) bldr.append("unrealizedProfit", unrealizedProfit.toPlainString());
            return bldr.toString();
        }
    }
}
