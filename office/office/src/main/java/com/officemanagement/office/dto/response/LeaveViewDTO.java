package com.officemanagement.office.dto.response;

import com.officemanagement.office.common.constant.LeaveType;
import com.officemanagement.office.common.constant.Frequency;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeaveViewDTO {
    private Long requestId;
    private Long userId;
    private LeaveType leaveType;
    private Frequency frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String reason;
    private LocalDateTime appliedOn; // corresponds to createdAt
}
