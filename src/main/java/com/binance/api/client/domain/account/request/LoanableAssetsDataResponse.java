package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanableAssetsDataResponse {
    private List<Row> rows;

    private long total;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {
        private String loanCoin;
        private BigDecimal _7dHourlyInterestRate;
        private BigDecimal _7dDailyInterestRate;
        private BigDecimal _14dHourlyInterestRate;
        private BigDecimal _14dDailyInterestRate;
        private BigDecimal _30dHourlyInterestRate;
        private BigDecimal _30dDailyInterestRate;
        private BigDecimal _90dHourlyInterestRate;
        private BigDecimal _90dDailyInterestRate;
        private BigDecimal _180dHourlyInterestRate;
        private BigDecimal _180dDailyInterestRate;
        private BigDecimal minLimit;
        private BigDecimal maxLimit;
        private int vipLevel;
    }
}
