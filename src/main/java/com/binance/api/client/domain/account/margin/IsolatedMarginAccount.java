package com.binance.api.client.domain.account.margin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsolatedMarginAccount {
    List<IsolatedMarginSymbolAsset> assets;
    BigDecimal totalAssetOfBtc;
    BigDecimal totalLiabilityOfBtc;
    BigDecimal totalNetAssetOfBtc;
}
