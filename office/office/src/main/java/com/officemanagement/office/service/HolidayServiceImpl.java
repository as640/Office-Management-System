package com.officemanagement.office.service;

import com.officemanagement.office.dao.model.HolidayEntity;
import com.officemanagement.office.dao.repository.HolidayRepository;
import com.officemanagement.office.dto.request.HolidayRequestDTO;
import com.officemanagement.office.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    @Override
    public List<HolidayEntity> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public HolidayEntity addHoliday(HolidayRequestDTO request) {
        // Prevent duplicate holiday on same title and date
        boolean exists = holidayRepository.existsByTitleIgnoreCaseAndHolidayDate(request.getTitle(), request.getHolidayDate());
        if (exists) {
            throw new RuntimeException("Holiday with the same title and date already exists.");
        }

        HolidayEntity holiday = new HolidayEntity();
        holiday.setTitle(request.getTitle());
        holiday.setHolidayDate(request.getHolidayDate());
        holiday.setDescription(request.getDescription());
        holiday.setRecurring(request.getRecurring());
        return holidayRepository.save(holiday);
    }

    @Override
    public HolidayEntity updateHoliday(Long id, HolidayRequestDTO request) {
        HolidayEntity holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found"));
        holiday.setTitle(request.getTitle());
        holiday.setHolidayDate(request.getHolidayDate());
        holiday.setDescription(request.getDescription());
        holiday.setRecurring(request.getRecurring());
        return holidayRepository.save(holiday);
    }
    @Override
    public HolidayEntity deleteHoliday(Long id) {
        HolidayEntity holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found"));
        holidayRepository.delete(holiday);
        return holiday;
    }

}
