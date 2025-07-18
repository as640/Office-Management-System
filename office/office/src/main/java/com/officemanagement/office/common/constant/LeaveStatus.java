package com.officemanagement.office.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveStatus {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    LeaveStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
