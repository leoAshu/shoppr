package com.leo.shoppr.dto.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatus {
    ERROR("error"),
    SUCCESS("success");

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
