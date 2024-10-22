package com.binance.api.client.domain.account;

import java.util.List;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DustTransferResponse {

    private String totalServiceCharge;
    private String totalTransfered;
    private List<TransferResult> transferResult;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("totalServiceCharge", totalServiceCharge)
                .append("totalTransfered", totalTransfered)
                .append("transferResult", transferResult)
                .toString();
    }
}