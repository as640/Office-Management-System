package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
}
