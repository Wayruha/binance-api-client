package com.binance.api.client.domain.account.margin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginSymbolAsset {
    String symbol;
    IsolatedMarginAsset baseAsset;
    IsolatedMarginAsset quoteAsset;
    boolean isolatedCreated;
    boolean enabled;
    BigDecimal marginLevel;
    MarginLevelStatus marginLevelStatus;
    BigDecimal marginRatio;
    BigDecimal indexPrice;
    BigDecimal liquidatePrice;
    BigDecimal liquidateRate;
    boolean tradeEnabled;

}
