package com.officemanagement.office.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaveType {

    SICK("SICK"),
    CASUAL("CASUAL"),
    EARNED("EARNED"),
    MATERNITY("MATERNITY"),
    PATERNITY("PATERNITY"),
    UNPAID("UNPAID"),
    COMPENSATORY("COMPENSATORY"),
    Mourning("MOURNING"),
    MARRIAGE("MARRIAGE"),
    STUDY("STUDY");

    private final String value;

    LeaveType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
