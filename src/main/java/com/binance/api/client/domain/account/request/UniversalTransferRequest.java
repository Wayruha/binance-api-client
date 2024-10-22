package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.UniversalTransferType;
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
public class UniversalTransferRequest {
    private UniversalTransferType type;
    private String asset;
    private BigDecimal amount;
    private String fromSymbol;
    private String toSymbol;
    private Long recvWindow;
    private Long timestamp;

    public UniversalTransferRequest(UniversalTransferType type, String asset, BigDecimal amount, String fromSymbol, String toSymbol) {
        this.type = type;
        this.asset = asset;
        this.amount = amount;
        this.fromSymbol = fromSymbol;
        this.toSymbol = toSymbol;
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
        this.timestamp = System.currentTimeMillis();
    }
}
