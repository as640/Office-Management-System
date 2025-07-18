package com.officemanagement.office.service;

import com.officemanagement.office.dao.model.AttendanceEntity;
import com.officemanagement.office.dao.repository.AttendanceRepository;
import com.officemanagement.office.dto.request.AttendanceSearchRequestDTO;
import com.officemanagement.office.dto.request.CheckInRequestDTO;
import com.officemanagement.office.dto.request.CheckOutRequestDTO;
import com.officemanagement.office.dto.response.CheckInResponseDTO;
import com.officemanagement.office.dto.response.CheckOutResponseDTO;
import com.officemanagement.office.dao.model.UserEntity;
import com.officemanagement.office.exception.AttendanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public CheckInResponseDTO markCheckIn(CheckInRequestDTO request) throws AttendanceException {
        if (request.getUserId() == null || request.getCheckInTime() == null) {
            throw new AttendanceException("User ID and check-in time must be provided.");
        }

        LocalDate checkInDate = request.getCheckInTime().toLocalDate();

        // Check if the user has already checked in on the given date
        List<AttendanceEntity> existing = attendanceRepository.findAll().stream()
                .filter(a -> a.getUserId().equals(request.getUserId())
                        && a.getCheckInTime() != null
                        && a.getCheckInTime().toLocalDate().equals(checkInDate))
                .toList();

        if (!existing.isEmpty()) {
            throw new AttendanceException("User has already checked in on this date: " + checkInDate);
        }

        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setUserId(request.getUserId());
        attendance.setCheckInTime(request.getCheckInTime());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());
        attendance.setStatus(AttendanceEntity.Status.Present);

        attendanceRepository.save(attendance);

        return new CheckInResponseDTO(
                attendance.getId(),
                attendance.getUserId(),
                attendance.getCheckInTime(),
                "Checked in successfully on " + checkInDate
        );
    }


    @Override
    public CheckOutResponseDTO markCheckOut(CheckOutRequestDTO request) {
        AttendanceEntity attendance = attendanceRepository.findById(request.getAttendanceId())
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        if (attendance.getCheckOutTime() != null) {
            throw new RuntimeException("User has already checked out today.");
        }

        attendance.setCheckOutTime(request.getCheckOutTime() != null ? request.getCheckOutTime() : LocalDateTime.now());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());
        attendance.setUpdatedAt(LocalDateTime.now());
        attendance.setUpdatedBy(attendance.getUserId());

        attendanceRepository.save(attendance);

        return new CheckOutResponseDTO(attendance.getId(), attendance.getUserId(), attendance.getCheckOutTime(), "Checked out successfully.");
    }

    @Override
    public CheckInResponseDTO editCheckIn(Long attendanceId, CheckInRequestDTO request) {
        AttendanceEntity attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setCheckInTime(request.getCheckInTime());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());
        attendance.setUpdatedAt(LocalDateTime.now());
        attendance.setUpdatedBy(request.getUserId());

        attendanceRepository.save(attendance);

        return new CheckInResponseDTO(attendance.getId(), attendance.getUserId(), attendance.getCheckInTime(), "Check-in updated successfully.");
    }

    @Override
    public CheckOutResponseDTO editCheckOut(Long attendanceId, CheckOutRequestDTO request) {
        AttendanceEntity attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setCheckOutTime(request.getCheckOutTime());
        attendance.setLatitude(request.getLatitude());
        attendance.setLongitude(request.getLongitude());
        attendance.setUpdatedAt(LocalDateTime.now());
        attendance.setUpdatedBy(request.getUserId());

        attendanceRepository.save(attendance);

        return new CheckOutResponseDTO(attendance.getId(), attendance.getUserId(), attendance.getCheckOutTime(), "Check-out updated successfully.");
    }

    @Override
    public List<AttendanceEntity> getAttendanceHistory(Long userId) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<AttendanceEntity> getAttendanceByDate(Long userId, LocalDate date) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getUserId().equals(userId)
                        && a.getCheckInTime() != null
                        && a.getCheckInTime().toLocalDate().equals(date))
                .toList();
    }

    @Override
    public List<AttendanceEntity> getAttendanceByMonth(Long userId, YearMonth month) {
        return attendanceRepository.findAll().stream()
                .filter(a -> a.getUserId().equals(userId)
                        && a.getCheckInTime() != null
                        && YearMonth.from(a.getCheckInTime().toLocalDate()).equals(month))
                .toList();
    }

    @Override
    public Double getTotalWorkingHoursForMonth(Long userId, YearMonth month) {
        return getAttendanceByMonth(userId, month).stream()
                .mapToDouble(a -> {
                    if (a.getCheckInTime() != null && a.getCheckOutTime() != null) {
                        Duration duration = Duration.between(a.getCheckInTime(), a.getCheckOutTime());
                        return duration.toMinutes() / 60.0;
                    }
                    return 0.0;
                }).sum();
    }
    public List<AttendanceEntity> searchAttendance(AttendanceSearchRequestDTO dto) {
        LocalDateTime start = null;
        LocalDateTime end = null;

        if (dto.getDate() != null) {
            start = dto.getDate().atStartOfDay();
            end = dto.getDate().atTime(LocalTime.MAX);
        } else if (dto.getMonth() != null) {
            start = dto.getMonth().atDay(1).atStartOfDay();
            end = dto.getMonth().atEndOfMonth().atTime(LocalTime.MAX);
        }

        return attendanceRepository.searchAttendance(dto.getUserId(),null, start, end);
    }

}
