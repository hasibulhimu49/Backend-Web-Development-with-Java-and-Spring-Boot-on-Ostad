package com.url.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;
import java.time.LocalDateTime;


public record UrlRequest(
        @NotBlank(message = "Url cannot empty..")
        @URL
        String originalUrl,
        @NotNull
        LocalDateTime validity
) {

}
