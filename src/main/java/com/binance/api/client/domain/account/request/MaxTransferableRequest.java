package com.binance.api.client.domain.account.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaxTransferableRequest {
    private String asset;
    private String isolatedSymbol;
}
