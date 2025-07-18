package com.officemanagement.office.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckInResponseDTO {
    private String message;
    private LocalDateTime checkInTime;
    private Long attendanceId;
    private Long employeeId;

    public CheckInResponseDTO(Long attendanceId, Long employeeId, LocalDateTime checkInTime, String message) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.checkInTime = checkInTime;
        this.message = message;
    }
}
