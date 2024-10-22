package com.binance.api.client.domain.event.wrapper;

import com.binance.api.client.domain.event.AbstractEvent;
import lombok.*;

import java.util.List;

/**
 * Collection of one trade transaction wrappers related to one order on spot market.
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class SpotFullOrderEvent extends AbstractEvent {
    List<SpotPartialOrderEvent> partials;

    public SpotFullOrderEvent(final List<SpotPartialOrderEvent> partials) {
        super(partials.get(0));
        this.partials = partials;
    }
}
