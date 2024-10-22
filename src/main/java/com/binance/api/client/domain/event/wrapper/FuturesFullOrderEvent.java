package com.binance.api.client.domain.event.wrapper;

import com.binance.api.client.domain.event.AbstractEvent;
import lombok.*;

import java.util.List;

/**
 * Collection of one trade transaction wrappers related to one order on future market.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class FuturesFullOrderEvent extends AbstractEvent {
    List<FuturesPartialOrderEvent> partials;

    public FuturesFullOrderEvent(final List<FuturesPartialOrderEvent> partials) {
        super(partials.get(0));
        this.partials = partials;
    }
}
