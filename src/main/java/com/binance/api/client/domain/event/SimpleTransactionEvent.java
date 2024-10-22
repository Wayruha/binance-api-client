package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.binance.api.client.domain.TransactionType.*;


/**
 * This event may be part of simple ConvertAsset action
 * or Transfer to/from Futures action.
 * isAdd and delta shows whether this transaction added or removed asset
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class SimpleTransactionEvent extends AssetTransactionEvent {
    protected final BalanceUpdateEvent balanceUpdate;
    protected final AccountUpdateEvent accountUpdate;
    protected final String asset;
    protected final BigDecimal delta;
    protected final boolean isAdd;
    @Setter
    protected TransactionType type;
    @Setter
    protected boolean processed;

    public SimpleTransactionEvent(AccountUpdateEvent accountUpdate, BalanceUpdateEvent balanceUpdate) {
        super(accountUpdate, Arrays.asList(balanceUpdate));
        this.eventType = EventType.TRANSACTION;
        this.balanceUpdate = balanceUpdate;
        this.accountUpdate = accountUpdate;

        this.asset = balanceUpdate.getAsset();
        this.delta = new BigDecimal(balanceUpdate.getBalanceDelta());
        this.isAdd = delta.signum() >= 0;
        this.type = delta.signum() == 0 ? NONE : delta.signum() > 0 ? ADD : DEDUCT;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("type", type)
                .append("asset", asset)
                .append("delta", delta)
                .toString();
    }
}
