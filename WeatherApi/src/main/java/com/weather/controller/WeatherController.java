package com.weather.controller;


import com.weather.deo.ApiResponse;
import com.weather.deo.WeatherResponse;
import com.weather.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<ApiResponse<WeatherResponse>> getWeather(
            @RequestParam @NotBlank(message = "City is required") String city
    ) {
        return ResponseEntity.ok(weatherService.getWeatherByCity(city));
    }
}
