package com.officemanagement.office.dao.repository;

import com.officemanagement.office.dao.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);        // Used as username
    boolean existsByName(String name);                   // Used to check duplicate username
}
