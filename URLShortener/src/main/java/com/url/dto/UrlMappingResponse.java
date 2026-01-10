package com.url.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public record UrlMappingResponse(
        String originalUrl,
        LocalDateTime validity
) {
}
