package com.authentication.file.dto.response;

import java.time.LocalDateTime;

public record FileDownloadResponse(
        String downloadLink,
        LocalDateTime expiaryTime
) {
}
