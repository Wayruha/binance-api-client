package com.binance.api.client.domain.account;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FuturesAccountBalance {
    //unique account code
    private String accountAlias;

    private String asset;

    private String balance;

    private String crossWalletBalance;
    // unrealized profit of crossed positions
    private String crossUnPnl;

    private String availableBalance;

    private String maxWithdrawAmount;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("accountAlias", accountAlias)
                .append("asset", asset)
                .append("balance", balance)
                .append("crossWalletBalance", crossWalletBalance)
                .append("crossUnPnl", crossUnPnl)
                .append("crossWalletBalance", crossWalletBalance)
                .append("availableBalance", availableBalance)
                .append("maxWithdrawAmount", maxWithdrawAmount)
                .toString();
    }
}
