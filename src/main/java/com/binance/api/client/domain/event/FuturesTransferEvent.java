package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.AccountUpdateReasonType;
import com.binance.api.client.domain.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Combined object which corresponds to 'transfer-to-futures' pattern:
 * FuturesAccountUpdateEvent->(BalanceUpdate->AccountUpdate) == SimpleTransactionEvent
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class FuturesTransferEvent extends SimpleTransactionEvent {
    private final FuturesAccountUpdateEvent futuresAccUpd;
    private final SimpleTransactionEvent baseTransferEvent;

    public FuturesTransferEvent(SimpleTransactionEvent baseEvent, FuturesAccountUpdateEvent futuresAccUpd) {
        super(baseEvent.getAccountUpdate(), baseEvent.getBalanceUpdate());
        this.baseTransferEvent = baseEvent;
        this.futuresAccUpd = futuresAccUpd;
        this.type = transferTypeFromReasonType(futuresAccUpd.getReasonType());
    }

    private static TransactionType transferTypeFromReasonType(AccountUpdateReasonType reason) {
        switch (reason) {
            case DEPOSIT:
                return TransactionType.SPOT_TO_FUTURES;
            case WITHDRAW:
                return TransactionType.FUTURES_TO_SPOT;
            default:
                throw new IllegalArgumentException("Unexpected AccountUpdateReasonType during creation of FuturesTransfer:" + reason);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventTime", eventTime)
                .append("type", type)
                .append("asset", asset)
                .append("delta", delta)
                .append("FutAccUpdate", futuresAccUpd)
                .toString();
    }
}
