package com.officemanagement.office.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CheckInRequestDTO {
    private Long userId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime checkInTime; // optional, if null it will be set in the service
}
