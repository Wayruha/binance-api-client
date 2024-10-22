package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.account.AssetBalance;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Account update event which will reflect the current position/balances of the account.
 * <p>
 * This event is embedded as part of a user data update event.
 *
 * @see UserDataUpdateEvent
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AccountUpdateEvent extends AbstractEvent {

    @JsonProperty("u")
    private Long lastUpdated;

    @JsonProperty("B")
    //@JsonDeserialize(contentUsing = AssetBalanceDeserializer.class)
    private List<AssetBalance> balances;

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventType", eventType)
                .append("eventTime", eventTime)
                .append("balances", balances)
                .toString();
    }
}
