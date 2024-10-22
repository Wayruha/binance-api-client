package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class CoinSwapEvent extends AbstractEvent {
    public CoinSwapEvent() {
        setEventType(EventType.COIN_SWAP_ORDER);
    }

    @JsonProperty("o")
    private String o; //TODO what is it?

    @JsonProperty("a")
    private String baseAsset;

    @JsonProperty("qa")
    private String quoteAsset;

    @JsonProperty("M")
    private String baseQty;

    @JsonProperty("m")
    private String quoteQty;

    @JsonProperty("p")
    private String price;

    @JsonProperty("ma")
    private String ma; //TODO what is it?

    @JsonProperty("sp")
    private String stopPrice;

    @JsonProperty("bp")
    private String basePrice; //TODO not sure what is it...

    @JsonProperty("t")
    private Long time;

    @JsonProperty("T")
    private Long transaction;

    @JsonProperty("s")
    private Boolean s; //TODO what is it?

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("baseAsset", baseAsset)
                .append("quoteAsset", quoteAsset)
                .append("baseQty", baseQty)
                .append("quoteQty", quoteQty)
                .append("price", price)
                .append("transaction", transaction)
                .append("s", s)
                .append("o", o)
                .append("ma", ma)
                .append("stopPrice", stopPrice)
                .append("basePrice", basePrice)
                .append("time", time)
                .append("transaction", transaction)
                .append("eventTime", eventTime)
                .toString();
    }

}
