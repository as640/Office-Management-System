package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
