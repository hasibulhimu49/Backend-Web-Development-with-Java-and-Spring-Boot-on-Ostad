package com.url.service;

import com.url.dto.UrlMappingResponse;
import com.url.dto.UrlRequest;
import com.url.dto.UrlResponse;
import jakarta.validation.Valid;

public interface UrlService {
    UrlResponse shortenUrl(@Valid UrlRequest request);

    UrlMappingResponse redirectUrl(String url);
}
