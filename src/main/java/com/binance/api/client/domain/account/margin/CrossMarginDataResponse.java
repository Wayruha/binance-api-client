package com.binance.api.client.domain.account.margin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrossMarginDataResponse {
    private String vipLevel;
    private String coin;
    private boolean transferIn;
    private boolean borrowable;
    private String dailyInterest;
    private String yearlyInterest;
    private String borrowLimit;
    private List<String> marginablePairs;
}
