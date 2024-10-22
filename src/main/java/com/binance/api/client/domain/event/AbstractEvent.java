package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractEvent {
    //duplicate from UserDataUpdateEvent
    protected Long transaction;
    @JsonProperty("e")
    protected EventType eventType;
    @JsonProperty("E")
    protected Long eventTime;

    public AbstractEvent() {
    }

    public AbstractEvent(AbstractEvent parentEvent) {
        this.eventType = parentEvent.getEventType();
        this.eventTime = parentEvent.getEventTime();
        this.transaction = parentEvent.getTransaction();
    }

    public boolean isSameTransaction(AbstractEvent event) {
        if (event == null) return false;
        return Objects.equals(eventTime, event.getEventTime()) || Objects.equals(transaction, event.getTransaction());
    }

    protected static BigDecimal toBigDecimal(String arg) {
        if (StringUtils.isEmpty(arg)) return BigDecimal.ZERO;
        return new BigDecimal(arg);
    }
}
