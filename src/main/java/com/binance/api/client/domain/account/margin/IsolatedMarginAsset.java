package com.binance.api.client.domain.account.margin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginAsset {
    private String asset;
    private boolean borrowEnabled;
    private boolean repayEnabled;
    private BigDecimal borrowed;
    private BigDecimal free;
    private BigDecimal interest;
    private BigDecimal locked;
    private BigDecimal netAsset;
    private BigDecimal netAssetOfBtc;
    private BigDecimal totalAsset;
}
