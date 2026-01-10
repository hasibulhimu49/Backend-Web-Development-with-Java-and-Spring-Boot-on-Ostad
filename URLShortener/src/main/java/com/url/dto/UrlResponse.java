package com.url.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public record UrlResponse(
        String shortUrl,
        String originalUrl,
        LocalDateTime validity
) {

}
