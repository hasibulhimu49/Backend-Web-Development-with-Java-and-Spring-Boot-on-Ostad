package com.weather.service.impl;
import com.weather.deo.ApiResponse;
import com.weather.deo.WeatherResponse;
import com.weather.service.WeatherService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    void check() {
        System.out.println("API KEY loaded: " + (apiKey != null));
    }

    @Override
    public ApiResponse<WeatherResponse> getWeatherByCity(String city) {

        String url = baseUrl + "?key=" + apiKey + "&q=" + city;

        Map response = restTemplate.getForObject(url, Map.class);

        Map location = (Map) response.get("location");
        Map current = (Map) response.get("current");

        WeatherResponse weatherResponse = new WeatherResponse(
                ((Double) location.get("lat")).doubleValue(),
                ((Double) location.get("lon")).doubleValue(),
                ((Number) current.get("temp_c")).doubleValue(),
                ((Number) current.get("wind_kph")).doubleValue(),
                ((Integer) current.get("humidity")).intValue()
        );

        return new ApiResponse<>(
                HttpStatus.OK.toString(),
                HttpStatus.OK.value(),
                "Weather data for city: " + city,
                false,
                weatherResponse
        );
    }
}