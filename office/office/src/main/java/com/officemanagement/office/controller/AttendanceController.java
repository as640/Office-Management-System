package com.officemanagement.office.controller;

import com.officemanagement.office.dao.model.AttendanceEntity;
import com.officemanagement.office.dto.request.AttendanceSearchRequestDTO;
import com.officemanagement.office.dto.request.CheckInRequestDTO;
import com.officemanagement.office.dto.request.CheckOutRequestDTO;
import com.officemanagement.office.dto.response.CheckInResponseDTO;
import com.officemanagement.office.dto.response.CheckOutResponseDTO;
import com.officemanagement.office.exception.AttendanceException;
import com.officemanagement.office.service.AttendanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/search")
    public ResponseEntity<List<AttendanceEntity>> searchAttendance(@RequestBody AttendanceSearchRequestDTO request) {
        return ResponseEntity.ok(attendanceService.searchAttendance(request));
    }
    @PostMapping("/check-in")
    public ResponseEntity<CheckInResponseDTO> markCheckIn(@RequestBody CheckInRequestDTO request) throws AttendanceException {
        return ResponseEntity.ok(attendanceService.markCheckIn(request));
    }

    @PostMapping("/check-out")
    public ResponseEntity<CheckOutResponseDTO> markCheckOut(@RequestBody CheckOutRequestDTO request) {
        return ResponseEntity.ok(attendanceService.markCheckOut(request));
    }

    @PatchMapping("/check-in/{attendanceId}")
    public ResponseEntity<CheckInResponseDTO> editCheckIn(
            @PathVariable Long attendanceId,
            @RequestBody CheckInRequestDTO request) {
        return ResponseEntity.ok(attendanceService.editCheckIn(attendanceId, request));
    }

    @PatchMapping("/check-out/{attendanceId}")
    public ResponseEntity<CheckOutResponseDTO> editCheckOut(
            @PathVariable Long attendanceId,
            @RequestBody CheckOutRequestDTO request) {
        return ResponseEntity.ok(attendanceService.editCheckOut(attendanceId, request));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(attendanceService.getAttendanceHistory(userId));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceByDate(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(userId, date));
    }

    @GetMapping("/by-month")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceByMonth(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(attendanceService.getAttendanceByMonth(userId, month));
    }

    @GetMapping("/total-hours")
    public ResponseEntity<Double> getTotalWorkingHoursForMonth(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        return ResponseEntity.ok(attendanceService.getTotalWorkingHoursForMonth(userId, month));
    }
}
