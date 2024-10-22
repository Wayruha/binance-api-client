package com.binance.api.client;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Binance API error object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BinanceApiError {

    /**
     * Error code.
     */
    private Integer code;

    /**
     * Error message.
     */
    private String msg;

    private String path;

    private Long timestamp;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("code", code)
                .append("msg", msg)
                .append("path", path)
                .append("timestamp", timestamp)
                .toString();
    }
}
