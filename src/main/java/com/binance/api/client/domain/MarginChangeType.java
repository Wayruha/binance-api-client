package com.binance.api.client.domain;

import com.binance.api.client.exception.UnsupportedEventException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum MarginChangeType {
    ADD("1"), //add position margin
    REDUCE("2"); //reduce position marging

    private final String value;

    MarginChangeType(String value) {
        this.value = value;
    }

    public static MarginChangeType fromTypeValue(String value) {
        return Stream.of(values()).filter(type -> type.value.equals(value)).findFirst()
                .orElseThrow(() -> new UnsupportedEventException("Unrecognized margin change type: " + value));
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
