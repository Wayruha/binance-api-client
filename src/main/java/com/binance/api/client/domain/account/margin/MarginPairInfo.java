package com.binance.api.client.domain.account.margin;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginPairInfo {
    private Long id;
    private String symbol;
    private String base;
    private String quote;
    private Boolean isMarginTrade;
    private Boolean isBuyAllowed;
    private Boolean isSellAllowed;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("id", id)
                .append("symbol", symbol)
                .append("base", base)
                .append("quote", quote)
                .append("isMarginTrade", isMarginTrade)
                .append("isBuyAllowed", isBuyAllowed)
                .append("isSellAllowed", isSellAllowed)
                .toString();
    }
}
