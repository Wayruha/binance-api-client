package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeLeverageResponse {
    private String symbol;
    private Integer leverage;
    private BigDecimal maxNotionalValue;
}
