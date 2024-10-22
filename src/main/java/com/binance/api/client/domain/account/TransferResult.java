package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Dust transfer result information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TransferResult {
    private String amount;
    private String fromAsset;
    private Long operateTime;
    private String serviceChargeAmount;
    private Long tranId;
    private String transferedAmount;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("amount", amount)
                .append("fromAsset", fromAsset)
                .append("transferedAmount", transferedAmount)
                .append("operateTime", operateTime)
                .append("tranId", tranId)
                .append("serviceChargeAmount", serviceChargeAmount)
                .toString();
    }
}