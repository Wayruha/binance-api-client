package com.binance.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanableAssetsDataRequest {
    private String loanCoin;
    private Integer vipLevel;
}
