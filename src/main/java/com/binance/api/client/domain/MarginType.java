package com.binance.api.client.domain;

import com.binance.api.client.exception.UnsupportedEventException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum MarginType {
    ISOLATED("isolated"),
    CROSSED("cross");

    private final String shortName;

    MarginType(String shortName) {
        this.shortName = shortName;
    }

    public static MarginType fromShortName(String name) {
        return Stream.of(values()).filter(type -> type.shortName.equals(name)).findFirst()
                .orElseThrow(() -> new UnsupportedEventException("Unrecognized margin type: " + name));
    }

    @JsonValue
    public String getShortName() {
        return this.name();
    }
}
