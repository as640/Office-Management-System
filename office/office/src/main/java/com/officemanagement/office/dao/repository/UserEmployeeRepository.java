package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.UserEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmployeeRepository extends JpaRepository<UserEmployeeEntity, Long> {
}
