package com.binance.api.client.domain.event;

import com.binance.api.client.exception.UnsupportedEventException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum EventType {
    ACCOUNT_POSITION_UPDATE("outboundAccountPosition"),
    BALANCE_UPDATE("balanceUpdate"),
    ORDER_TRADE_UPDATE("executionReport"),
    FUTURES_ORDER_TRADE_UPDATE("ORDER_TRADE_UPDATE"),
    FUTURES_ACCOUNT_UPDATE("ACCOUNT_UPDATE"),
    LISTEN_KEY_EXPIRED("listenKeyExpired"),
    ACCOUNT_CONFIG_UPDATE("ACCOUNT_CONFIG_UPDATE"), //only for leverage change in Binance Futures,
    COIN_SWAP_ORDER("COIN_SWAP_ORDER"),
    MARGIN_CALL("MARGIN_CALL"),
    OCO_TRADE_UPDATE("listStatus"), //TODO deserialize

    //custom types, Binance does not know about them
    TRANSACTION("TRANSACTION"),
    CONVERT_FUNDS("CONVERT_FUNDS");

    private final String eventTypeId;

    EventType(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    @JsonValue
    public String getEventTypeId() {
        return eventTypeId;
    }

    public static EventType fromEventTypeId(String eventTypeId) {
        return Stream.of(values()).filter(type -> type.eventTypeId.equals(eventTypeId)).findFirst()
                .orElseThrow(() -> new UnsupportedEventException("Unrecognized user data update event type id: " + eventTypeId));
    }
}
