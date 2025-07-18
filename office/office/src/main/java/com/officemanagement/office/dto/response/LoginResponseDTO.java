package com.officemanagement.office.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String token;
    private Long userId;
    private String role;
    private String name;
    private String email;
    private String department;
    private String designation;

}
