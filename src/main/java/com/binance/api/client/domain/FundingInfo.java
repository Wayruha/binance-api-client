package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundingInfo {
    private String symbol;

    @JsonProperty("markPrice")
    private BigDecimal markPrice;

    @JsonProperty("indexPrice")
    private BigDecimal indexPrice;

    @JsonProperty("estimatedSettlePrice")
    private BigDecimal estimatedSettlePrice;

    @JsonProperty("lastFundingRate")
    private BigDecimal lastFundingRate;

    @JsonProperty("interestRate")
    private BigDecimal interestRate;

    @JsonProperty("nextFundingTime")
    private long nextFundingTime;

    @JsonProperty("time")
    private long time;
}
