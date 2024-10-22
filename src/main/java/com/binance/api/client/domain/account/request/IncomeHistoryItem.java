package com.binance.api.client.domain.account.request;

import com.binance.api.client.FuturesIncomeType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeHistoryItem {
    private String symbol;
    private FuturesIncomeType incomeType;
    private BigDecimal income;
    private String asset;
    private String info;
    private long time;
    private String tranId;
    private String tradeId;
}
