package com.url.controller;

import com.url.dto.UrlMappingResponse;
import com.url.dto.UrlRequest;
import com.url.dto.UrlResponse;
import com.url.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    private  UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<UrlResponse> createShortUrl(@Valid @RequestBody UrlRequest request){
        return ResponseEntity.ok(urlService.shortenUrl(request));
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<UrlMappingResponse> redirectUrl(@PathVariable String shortCode){
        return ResponseEntity.ok(urlService.redirectUrl(shortCode));
    }
}
