package com.authentication.file.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ShareFileRequest(
        Long fileId,
        List<String> emails
) {
}
