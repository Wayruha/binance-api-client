package com.binance.api.client.domain.account.margin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginDataResponse {
    private String vipLevel;
    private String symbol;
    private String leverage;
    private List<AssetData> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AssetData {
        private String coin;
        private String dailyInterest;
        private String borrowLimit;
    }
}
