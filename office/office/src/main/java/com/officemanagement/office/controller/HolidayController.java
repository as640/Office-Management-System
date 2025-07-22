package com.officemanagement.office.controller;

import com.officemanagement.office.dao.model.HolidayEntity;
import com.officemanagement.office.dto.request.HolidayRequestDTO;
import com.officemanagement.office.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<HolidayEntity>> getAllHolidays() {
        return ResponseEntity.ok(holidayService.getAllHolidays());
    }

    @PostMapping
    public ResponseEntity<HolidayEntity> addHoliday(@RequestBody HolidayRequestDTO request) {
        return ResponseEntity.ok(holidayService.addHoliday(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HolidayEntity> updateHoliday(@PathVariable Long id, @RequestBody HolidayRequestDTO request) {
        return ResponseEntity.ok(holidayService.updateHoliday(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HolidayEntity> deleteHoliday(@PathVariable Long id) {
        return ResponseEntity.ok(holidayService.deleteHoliday(id));
    }

}
