package com.officemanagement.office.service;

import com.officemanagement.office.common.constant.LeaveStatus;
import com.officemanagement.office.common.constant.LeaveType;
import com.officemanagement.office.dao.model.LeaveBalanceEntity;
import com.officemanagement.office.dao.model.LeaveRequestEntity;
import com.officemanagement.office.dao.repository.LeaveBalanceRepository;
import com.officemanagement.office.dao.repository.LeaveRequestRepository;
import com.officemanagement.office.dao.repository.UserRepository;
import com.officemanagement.office.dto.request.ApplyLeaveRequestDTO;
import com.officemanagement.office.exception.LeaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final UserRepository userRepository;

    @Override
    public void applyLeave(ApplyLeaveRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();

        userRepository.findById(userId)
                .orElseThrow(() -> new LeaveException("User not found"));

        LeaveType leaveType = requestDTO.getLeaveType();

        LeaveBalanceEntity balance = leaveBalanceRepository.findById(userId)
                .orElseThrow(() -> new LeaveException("Leave balance not found"));

        LocalDate start = requestDTO.getStartDate();
        LocalDate end = requestDTO.getEndDate();

        if (end.isBefore(start)) {
            throw new LeaveException("End date cannot be before start date");
        }

        // Check for overlapping leaves for the same user
        List<LeaveRequestEntity> existingLeaves = leaveRequestRepository
                .findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(userId, end, start);

        if (!existingLeaves.isEmpty()) {
            throw new LeaveException("You already have a leave applied for this date range.");
        }

        long days = ChronoUnit.DAYS.between(start, end) + 1;

        float availableLeaves;
        if (leaveType == LeaveType.SICK) {
            availableLeaves = balance.getSickLeave() != null ? balance.getSickLeave() : 0f;
        } else if (leaveType == LeaveType.EARNED) {
            availableLeaves = balance.getEarnedLeave() != null ? balance.getEarnedLeave() : 0f;
        } else {
            throw new LeaveException("Leave type not supported");
        }

        if (availableLeaves < days) {
            throw new LeaveException("Insufficient leave balance");
        }

        LeaveRequestEntity request = new LeaveRequestEntity();
        request.setUserId(userId);
        request.setStartDate(start);
        request.setEndDate(end);
        request.setLeaveType(leaveType);
        request.setFrequency(convertFrequency(requestDTO.getFrequency()));
        request.setReason(requestDTO.getReason());
        request.setStatus(LeaveStatus.PENDING);
        request.setCreatedAt(LocalDateTime.now());
        request.setCreatedBy(userId);

        // Deduct leave balance
        if (leaveType == LeaveType.SICK) {
            balance.setSickLeave(availableLeaves - days);
        } else if (leaveType == LeaveType.EARNED) {
            balance.setEarnedLeave(availableLeaves - days);
        }

        leaveBalanceRepository.save(balance);
        leaveRequestRepository.save(request);
    }

    @Override
    public void editLeave(Long requestId, ApplyLeaveRequestDTO dto) {
        LeaveRequestEntity request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new LeaveException("Leave request not found"));

        if (!request.getUserId().equals(dto.getUserId())) {
            throw new LeaveException("Unauthorized to edit this leave");
        }

        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setLeaveType(dto.getLeaveType());
        request.setFrequency(convertFrequency(dto.getFrequency()));
        request.setReason(dto.getReason());
        request.setUpdatedAt(LocalDateTime.now());
        request.setUpdatedBy(dto.getUserId());
        request.setStatus(LeaveStatus.PENDING); // Reset status to PENDING after edit

        leaveRequestRepository.save(request);
    }

    @Override
    public void rejectLeave(Long requestId, Long managerId, String reason) {
        LeaveRequestEntity request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new LeaveException("Leave request not found"));

        request.setStatus(LeaveStatus.REJECTED);
        request.setUpdatedAt(LocalDateTime.now());
        request.setUpdatedBy(managerId);

        leaveRequestRepository.save(request);
    }

    @Override
    public void approveLeave(Long requestId, Long managerId) {
        LeaveRequestEntity request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new LeaveException("Leave request not found"));

        request.setStatus(LeaveStatus.APPROVED);
        request.setUpdatedAt(LocalDateTime.now());
        request.setUpdatedBy(managerId);

        leaveRequestRepository.save(request);
    }

    // Helper method to convert Frequency enum from DTO to Entity
    private LeaveRequestEntity.Frequency convertFrequency(com.officemanagement.office.common.constant.Frequency freq) {
        if (freq == null) return null;
        return switch (freq) {
            case DAY -> LeaveRequestEntity.Frequency.DAY;
            case HALF_DAY -> LeaveRequestEntity.Frequency.HALF_DAY;
            case WEEKLY -> LeaveRequestEntity.Frequency.WEEKLY;
            case MONTHLY -> LeaveRequestEntity.Frequency.MONTHLY;
            case HALF_YEARLY -> LeaveRequestEntity.Frequency.HALF_YEARLY;
        };
    }
}
