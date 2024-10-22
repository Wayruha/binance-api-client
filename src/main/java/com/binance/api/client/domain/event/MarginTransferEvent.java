package com.binance.api.client.domain.event;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(exclude = "relatedAccUpdate")
public class MarginTransferEvent extends AbstractEvent {
    private final FuturesAccountUpdateEvent relatedAccUpdate;
    private final FuturesAccountUpdateEvent.Position position;
    private BigDecimal marginDelta;

    public MarginTransferEvent(FuturesAccountUpdateEvent relatedAccUpdate, FuturesAccountUpdateEvent.Position position) {
        this.relatedAccUpdate = relatedAccUpdate;
        this.position = position;
    }
}
