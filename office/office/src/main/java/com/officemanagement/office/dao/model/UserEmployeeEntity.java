package com.officemanagement.office.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;

@Entity
@Table(name = "users_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEmployeeEntity {

    @Id
    private Long userId;

    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    // Optional: If you want entity relations:

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "user_id")
//    private UserEntity user;

//    @OneToOne
//    @JoinColumn(name = "employee_id", unique = true)
//    private EmployeeEntity employee;

}

