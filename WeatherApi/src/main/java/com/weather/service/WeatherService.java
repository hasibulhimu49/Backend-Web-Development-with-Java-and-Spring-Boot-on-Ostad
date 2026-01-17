package com.weather.service;

import com.weather.deo.ApiResponse;
import com.weather.deo.WeatherResponse;
import jakarta.validation.constraints.NotBlank;

public interface WeatherService {
    ApiResponse<WeatherResponse> getWeatherByCity(@NotBlank(message = "City is required") String city);
}
