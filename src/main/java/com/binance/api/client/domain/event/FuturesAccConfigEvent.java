package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.MarginType;
import com.binance.api.client.domain.account.FuturesPosition;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"marginDetails", "isHedgeMode"})
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FuturesAccConfigEvent extends AbstractEvent {
    @JsonProperty("ac")
    private LeverageDetails leverageConfig;

    @JsonProperty("ai")
    private MultiAssetsCfg multiAssetsCfg;

    // manually constructed - does not come from Binance
    private MarginDetails marginDetails;
    private Boolean isHedgeMode;

    public FuturesAccConfigEvent(LeverageDetails leverageConfig, MultiAssetsCfg multiAssetsCfg, MarginDetails marginDetails) {
        this.leverageConfig = leverageConfig;
        this.multiAssetsCfg = multiAssetsCfg;
        this.marginDetails = marginDetails;
    }

    public FuturesAccConfigEvent(AbstractEvent parentEvent) {
        super(parentEvent);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("leverageConfig", leverageConfig)
                .append("multiAssetsCfg", multiAssetsCfg)
                .append("marginDetails", marginDetails)
                .append("hedgeMode", isHedgeMode)
                .toString();
    }

    @NoArgsConstructor
    @ToString
    public static class MarginDetails {
        @Getter
        private Map<String, MarginType> symbolMarginType;

        public MarginDetails(List<FuturesPosition> positionList) {
            this.symbolMarginType = positionList.stream()
                    .collect(Collectors.toMap(FuturesPosition::getSymbol, FuturesPosition::getMarginType, (t1, t2) -> t2));
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class LeverageDetails {
        @JsonProperty("s")
        private String symbol;

        @JsonProperty("l")
        private Integer leverage;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MultiAssetsCfg {
        @JsonProperty("j")
        Boolean isMultiAssets;
    }
}
