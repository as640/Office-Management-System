package com.officemanagement.office.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayRequestDTO {
    private String title;
    private LocalDate holidayDate;
    private String description;
    private Boolean recurring;
}
