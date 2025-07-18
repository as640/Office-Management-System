package com.officemanagement.office.common.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Frequency {

    DAY("DAY"),
    HALF_DAY("HALF_DAY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY"),
    HALF_YEARLY("HALF_YEARLY");

    private final String value;

    Frequency(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
