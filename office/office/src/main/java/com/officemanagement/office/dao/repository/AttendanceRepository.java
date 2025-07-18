package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    Optional<AttendanceEntity> findByUserIdAndCheckInTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT a FROM AttendanceEntity a " +
            "WHERE (:userId IS NULL OR a.userId = :userId) " +
            "AND (:date IS NULL OR DATE(a.checkInTime) = :date) " +
            "AND (:monthStart IS NULL OR a.checkInTime >= :monthStart) " +
            "AND (:monthEnd IS NULL OR a.checkInTime <= :monthEnd)")
    List<AttendanceEntity> searchAttendance(
            @Param("userId") Long userId,
            @Param("date") LocalDate date,
            @Param("monthStart") LocalDateTime monthStart,
            @Param("monthEnd") LocalDateTime monthEnd
    );
}
