package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AssetTransactionEvent extends AbstractEvent implements IdempotentUpdate {
    protected final List<BalanceUpdateEvent> balanceUpdates;
    protected final AccountUpdateEvent accountUpdate;
    protected final List<String> assets;
    @Setter
    protected boolean processed;

    public AssetTransactionEvent(AccountUpdateEvent accountUpdate, List<BalanceUpdateEvent> balanceUpdates) {
        super(accountUpdate);
        this.eventType = EventType.TRANSACTION;
        this.balanceUpdates = balanceUpdates;
        this.accountUpdate = accountUpdate;
        this.assets = balanceUpdates.stream().map(BalanceUpdateEvent::getAsset).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("assets", assets)
                .toString();
    }
}
