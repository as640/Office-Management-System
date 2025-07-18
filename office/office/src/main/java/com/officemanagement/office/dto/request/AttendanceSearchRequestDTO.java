package com.officemanagement.office.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
public class AttendanceSearchRequestDTO {
    private Long userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date; // optional: for search by date

    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth month; // optional: for search by month
}
