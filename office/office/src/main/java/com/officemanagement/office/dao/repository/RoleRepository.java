package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
