package com.binance.api.client.domain.account.margin;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CrossMarginAssetInfo {
    private String assetFullName;
    private String assetName;
    private Boolean isBorrowable;
    private Boolean isMortgageable;
    private BigDecimal userMinBorrow;
    private BigDecimal userMinRepay;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("assetFullName", assetFullName)
                .append("assetName", assetName)
                .append("isBorrowable", isBorrowable)
                .append("userMinBorrow", userMinBorrow)
                .append("userMinRepay", userMinRepay)
                .append("isMortgageable", isMortgageable)
                .toString();
    }
}
