package com.binance.api.client.domain.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeeStructure {
    private String symbol;
    private BigDecimal makerCommission;
    private BigDecimal takerCommission;
}
