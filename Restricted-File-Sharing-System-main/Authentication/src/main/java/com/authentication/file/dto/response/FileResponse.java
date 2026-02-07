package com.authentication.file.dto.response;

import lombok.Builder;

@Builder
public record FileResponse(
        Long id,
        String filepath
) {
}
