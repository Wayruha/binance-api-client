package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginLoanRequest {
    private String asset;
    private BigDecimal amount;
    private String symbol;
    private boolean isIsolated;
    private Long recvWindow;
    private Long timestamp;

    public MarginLoanRequest(String symbol, String asset, BigDecimal amount, boolean isIsolated) {
        this.symbol = symbol;
        this.asset = asset;
        this.amount = amount;
        this.isIsolated = isIsolated;
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
        this.timestamp = System.currentTimeMillis();
    }
}
