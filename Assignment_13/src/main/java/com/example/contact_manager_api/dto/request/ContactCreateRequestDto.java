package com.example.contact_manager_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactCreateRequestDto {

    @NotNull(message = "First name can not be null")
    @JsonProperty("firstname")
    String firstName;

    @JsonProperty("lastname")
    String lastName;

    String phone;

    @Email
    String email;


    @JsonProperty("active")
    Boolean isActive;

    String category;


}
