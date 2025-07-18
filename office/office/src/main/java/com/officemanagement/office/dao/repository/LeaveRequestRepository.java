package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Long> {

    // Check if user already has a leave overlapping with given date range
    List<LeaveRequestEntity> findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long userId,
            LocalDate endDate,
            LocalDate startDate
    );
}
