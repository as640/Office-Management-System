package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.ApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
}
