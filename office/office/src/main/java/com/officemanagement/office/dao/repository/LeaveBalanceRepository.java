package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.LeaveBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalanceEntity, Long> {
    Optional<LeaveBalanceEntity> findByUserId(Long userId);
}

