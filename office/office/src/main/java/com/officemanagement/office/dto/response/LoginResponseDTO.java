package com.officemanagement.office.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String role;
    private String message;
}
