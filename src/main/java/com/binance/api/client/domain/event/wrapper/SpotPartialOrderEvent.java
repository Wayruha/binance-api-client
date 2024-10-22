package com.binance.api.client.domain.event.wrapper;

import com.binance.api.client.domain.event.*;
import lombok.*;

/**
 * Wrapper for ws events related to one trade transaction (trade event & account update) on spot market.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class SpotPartialOrderEvent extends AbstractEvent {
    OrderTradeUpdateEvent orderTradeUpdateEvent;
    AccountUpdateEvent accountUpdateEvent;

    public SpotPartialOrderEvent(final OrderTradeUpdateEvent orderTradeUpdateEvent,
                                 final AccountUpdateEvent accountUpdateEvent) {
        super(orderTradeUpdateEvent);
        this.orderTradeUpdateEvent = orderTradeUpdateEvent;
        this.accountUpdateEvent = accountUpdateEvent;
    }
}
