package com.officemanagement.office.service;

import com.officemanagement.office.dto.request.ApplyLeaveRequestDTO;

public interface LeaveService {
    void applyLeave(ApplyLeaveRequestDTO requestDTO);
    void editLeave(Long requestId, ApplyLeaveRequestDTO dto);
    void rejectLeave(Long requestId, Long managerId, String reason);
    void approveLeave(Long requestId, Long managerId);
}
