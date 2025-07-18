package com.officemanagement.office.service;

import com.officemanagement.office.dao.model.HolidayEntity;
import com.officemanagement.office.dto.request.HolidayRequestDTO;

import java.util.List;

public interface HolidayService {
    List<HolidayEntity> getAllHolidays();
    HolidayEntity addHoliday(HolidayRequestDTO request);
    HolidayEntity updateHoliday(Long id, HolidayRequestDTO request);
}
