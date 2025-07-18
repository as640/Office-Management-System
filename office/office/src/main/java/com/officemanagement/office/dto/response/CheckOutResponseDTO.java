package com.officemanagement.office.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckOutResponseDTO {
    private String message;
    private LocalDateTime checkOutTime;
    private Long attendanceId;
    private Long employeeId;

    public CheckOutResponseDTO(Long attendanceId, Long employeeId, LocalDateTime checkOutTime, String message) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.checkOutTime = checkOutTime;
        this.message = message;
    }
}
