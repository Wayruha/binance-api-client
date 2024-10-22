package com.binance.api.client.domain.event.wrapper;

import com.binance.api.client.domain.event.*;
import lombok.*;

/**
 * Wrapper for ws events related to one trade transaction (account update & trade event) on future market.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class FuturesPartialOrderEvent extends AbstractEvent {
    FuturesAccountUpdateEvent futuresAccountUpdateEvent;
    FuturesTradeEvent futuresTradeEvent;

    public FuturesPartialOrderEvent(final FuturesAccountUpdateEvent futuresAccountUpdateEvent,
                                    final FuturesTradeEvent futuresTradeEvent) {
        super(futuresAccountUpdateEvent);
        this.futuresAccountUpdateEvent = futuresAccountUpdateEvent;
        this.futuresTradeEvent = futuresTradeEvent;
    }
}
