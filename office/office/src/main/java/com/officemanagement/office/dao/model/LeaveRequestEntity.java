package com.officemanagement.office.dao.model;

import com.officemanagement.office.common.constant.LeaveStatus;
import com.officemanagement.office.common.constant.LeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column(columnDefinition = "TEXT")
    private String reason;

    public enum Frequency {
        DAY, HALF_DAY, WEEKLY, MONTHLY, HALF_YEARLY
    }
}
