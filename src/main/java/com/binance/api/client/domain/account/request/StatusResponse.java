package com.binance.api.client.domain.account.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.binance.api.client.constant.BinanceApiConstants.STATUS_OK;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponse {
    private Integer code;
    private String msg;

    public static boolean isValid(StatusResponse obj){
        if(obj == null) return false;
        return obj.code != null && obj.code == STATUS_OK;
    }
}
