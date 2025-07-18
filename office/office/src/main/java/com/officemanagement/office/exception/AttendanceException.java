package com.officemanagement.office.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class AttendanceException extends Exception {
    public AttendanceException(String message) {
        super(message);
    }
}
