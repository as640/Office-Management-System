package com.officemanagement.office.dto.request;

import com.officemanagement.office.common.constant.Frequency;
import com.officemanagement.office.common.constant.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ApplyLeaveRequestDTO {
    private Long userId;
    private String reason;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Frequency frequency;

}
