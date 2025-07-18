package com.officemanagement.office.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidayDTO {
    private Long id; // Added for referencing the holiday
    private String title; // Matches `title` column in DB
    private LocalDate holidayDate; // Matches `holiday_date` column
    private boolean recurring; // Matches `recurring` column
    private String description;
}
