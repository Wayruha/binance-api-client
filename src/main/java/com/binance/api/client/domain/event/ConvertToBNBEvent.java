package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ConvertToBNBEvent extends AbstractEvent implements IdempotentUpdate {
    @Setter
    protected boolean processed;
    private static final String BNB_SYMBOL = "BNB";

    private final List<AssetTransactionEvent> transfers;
    private final List<String> convertedAssets;

    public ConvertToBNBEvent(List<AssetTransactionEvent> transfers) {
        super(transfers.get(transfers.size() - 1).getAccountUpdate());
        for (AssetTransactionEvent event : transfers) {
            if (!validConvertToBNB(event)) {
                throw new IllegalArgumentException("Not valid event for ConvertToBNB:" + event);
            }
        }
        this.transfers = transfers;
        this.convertedAssets = transfers.stream()
                .flatMap(transfer -> transfer.getAssets().stream()).distinct()
                .filter(asset -> !BNB_SYMBOL.equals(asset)).collect(Collectors.toList());
    }

    public static boolean validConvertToBNB(AssetTransactionEvent event) {
        if (event.getClass() != AssetTransactionEvent.class)
            return false;
        final List<BalanceUpdateEvent> addEvents = event.getBalanceUpdates().stream().filter(b -> new BigDecimal(b.getBalanceDelta()).signum() > 0).collect(Collectors.toList());
        return addEvents.size() == 1 && Objects.equals(BNB_SYMBOL, addEvents.get(0).getAsset());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("assets", convertedAssets)
                .append("eventTime", eventTime)
                .toString();
    }
}
