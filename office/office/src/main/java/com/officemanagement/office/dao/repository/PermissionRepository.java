package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
