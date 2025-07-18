package com.officemanagement.office.dto.response;

import com.officemanagement.office.common.constant.LeaveStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyLeaveResponseDTO {
    private Boolean decision;       // true if applied/processed successfully
    private String message;         // any success/failure message
    private Long requestId;         // ID of the leave request in DB
    private LeaveStatus status;     // current status of the request (e.g., PENDING)
}
