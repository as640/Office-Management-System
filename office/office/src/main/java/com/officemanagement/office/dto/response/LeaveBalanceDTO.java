package com.officemanagement.office.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveBalanceDTO {
    private Long userId;
    private String name;        // Optional, useful for admin views
    private Float earnedLeave;
    private Float sickLeave;
}
