package com.officemanagement.office.dao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leave_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalanceEntity extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "earned_leave")
    private Float earnedLeave;

    @Column(name = "sick_leave")
    private Float sickLeave;
}
