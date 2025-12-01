package com.SLMS.dto.response;

import com.SLMS.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;

}
