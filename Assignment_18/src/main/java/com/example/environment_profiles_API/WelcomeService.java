package com.example.environment_profiles_API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    @Value("${app.message}")
    private String message;

    @Value("${app.api.url}")
    private String url;

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}