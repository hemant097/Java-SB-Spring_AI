package com.example.project.springai.entity;

import lombok.*;

@Data
public class ApiResponseDto {

    private String cityName;
    private String weatherDescription;
    private String temperatureInCelsius;
    private String pressure;
    private String humidity;

    public static ApiResponseDto of(ApiResponse response){

        ApiResponseDto apiResponseDto = new ApiResponseDto();

        apiResponseDto.setWeatherDescription(response.getWeather()[0].getMain()+", "+response.getWeather()[0].getDescription());
        apiResponseDto.setTemperatureInCelsius(String.valueOf(response.getMain().getTemp() - 273.15));
        apiResponseDto.setHumidity(String.valueOf(response.getMain().getHumidity()));
        apiResponseDto.setPressure(String.valueOf(response.getMain().getPressure()));
        apiResponseDto.setCityName(response.getName());

        return apiResponseDto;
    }

}
