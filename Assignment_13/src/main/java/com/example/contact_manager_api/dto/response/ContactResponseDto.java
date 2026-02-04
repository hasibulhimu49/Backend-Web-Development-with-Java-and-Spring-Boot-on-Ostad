package com.example.contact_manager_api.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactResponseDto {


    Long id;
    String firstName;
    String lastName;
    String phone;
    String email;
    Boolean isActive;
    String category;


    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
