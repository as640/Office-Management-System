package com.officemanagement.office.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayResponseDTO {
    private Long id;
    private String title;
    private LocalDate holidayDate;
    private String description;
    private Boolean recurring;
}
