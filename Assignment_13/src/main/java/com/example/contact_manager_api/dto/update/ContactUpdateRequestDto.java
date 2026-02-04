package com.example.contact_manager_api.dto.update;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUpdateRequestDto {


    @JsonProperty("firstname")
    String firstName;

    @JsonProperty("lastname")
    String lastName;
    String phone;

    @Email
    String email;

    Boolean isActive;

    String category;
}
