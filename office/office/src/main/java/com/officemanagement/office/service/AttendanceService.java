package com.officemanagement.office.service;

import com.officemanagement.office.dao.model.AttendanceEntity;
import com.officemanagement.office.dto.request.AttendanceSearchRequestDTO;
import com.officemanagement.office.dto.request.CheckInRequestDTO;
import com.officemanagement.office.dto.request.CheckOutRequestDTO;
import com.officemanagement.office.dto.response.CheckInResponseDTO;
import com.officemanagement.office.dto.response.CheckOutResponseDTO;
import com.officemanagement.office.exception.AttendanceException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {
    CheckInResponseDTO markCheckIn(CheckInRequestDTO request) throws AttendanceException;
    CheckOutResponseDTO markCheckOut(CheckOutRequestDTO request);

    CheckInResponseDTO editCheckIn(Long attendanceId, CheckInRequestDTO request);
    CheckOutResponseDTO editCheckOut(Long attendanceId, CheckOutRequestDTO request);

    List<AttendanceEntity> getAttendanceHistory(Long userId);
    List<AttendanceEntity> getAttendanceByDate(Long userId, LocalDate date);
    List<AttendanceEntity> getAttendanceByMonth(Long userId, YearMonth month);

    Double getTotalWorkingHoursForMonth(Long userId, YearMonth month);


    List<AttendanceEntity> searchAttendance(AttendanceSearchRequestDTO request);

}
