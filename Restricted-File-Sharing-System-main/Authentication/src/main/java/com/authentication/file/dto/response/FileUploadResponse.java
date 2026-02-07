package com.authentication.file.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FileUploadResponse(
        String downloadToken
) {
}
