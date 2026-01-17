package com.weather.deo;


public record WeatherResponse(
         double latitude,
         double longitude,
         double temperatureCelsius,
         double windKmh,
         int humidity
) {
}
