package com.officemanagement.office.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CheckOutRequestDTO {
    private Long userId;          // renamed from employeeId
    private Long attendanceId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime checkOutTime;
}
