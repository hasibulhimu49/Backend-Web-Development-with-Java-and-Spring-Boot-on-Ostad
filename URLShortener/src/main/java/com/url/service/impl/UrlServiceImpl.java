package com.url.service.impl;

import com.url.dto.UrlMappingResponse;
import com.url.dto.UrlRequest;
import com.url.dto.UrlResponse;
import com.url.entity.ShortUrl;
import com.url.exception.handler.UrlExpiredException;
import com.url.exception.handler.UrlNotFoundException;
import com.url.repository.UrlRepository;
import com.url.service.UrlService;
import com.url.util.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository repository;

    @Override
    public UrlResponse shortenUrl(UrlRequest request) {
        if (request.validity().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Validity must be in future");
        }

        ShortUrl url = repository.findByOriginalUrl(request.originalUrl()).orElse(null);
        if(url != null){
            UrlResponse response = new UrlResponse(
                    "http://localhost:8080/r/" + url.getShortUrl(),
                    url.getOriginalUrl(),
                    url.getValidity()
            );
            return response;
        }

        String shortCode;
        do {
            shortCode = ShortCodeGenerator.generate(9);
        } while (repository.existsByShortUrl(shortCode));

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(request.originalUrl())
                .shortUrl(shortCode)
                .validity(request.validity())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(shortUrl);

        UrlResponse response = new UrlResponse(
                "http://localhost:8080/r/"+shortUrl.getShortUrl(),
                shortUrl.getOriginalUrl(),
                shortUrl.getValidity()
        );
        return response;

    }

    @Override
    public UrlMappingResponse redirectUrl(String url) {
        ShortUrl shortUrl = repository.findByShortUrl(url).orElseThrow(
                ()-> new UrlNotFoundException("Url not found with url: '"+url+"'")
        );

        if(shortUrl.getValidity().isBefore(LocalDateTime.now())){
            throw new UrlExpiredException("Url expired");
        }

        UrlMappingResponse response = new UrlMappingResponse(
                shortUrl.getOriginalUrl(),
                shortUrl.getValidity()
        );

        return response;
    }

}
