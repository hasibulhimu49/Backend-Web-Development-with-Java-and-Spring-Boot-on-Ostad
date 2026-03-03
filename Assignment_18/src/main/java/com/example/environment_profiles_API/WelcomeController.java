package com.example.environment_profiles_API;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/status")
@AllArgsConstructor
public class WelcomeController {

    private final WelcomeService service;

    @GetMapping
    public Map<String,String> getStatus()
    {
        Map<String,String> response=new HashMap<>();
        response.put("message",service.getMessage());
        response.put("url",service.getUrl());
        return response;
    }
}
