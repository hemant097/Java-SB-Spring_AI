package com.example.project.springai.tool;

import com.example.project.springai.entity.ApiResponseDto;
import com.example.project.springai.exception.UnknownAPIException;
import com.example.project.springai.entity.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravellingTool {

    private final RestClient restClient;

    @Value("${API_KEY}")
    String weatherAPI_KEY;

    @Tool(description = "get the weather of a city")
    public ApiResponseDto getWeather(@ToolParam(description = "city name for which to get the weather information") String city){
        return getWeatherFromAPI(city);
    }

    public ApiResponseDto getWeatherFromAPI(String city){

        //The api returns a JSON response, we've mapped the required fields from that into our ApiResponse class
        ApiResponse weatherApiResponse =  restClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q",city)
                        .queryParam("appid", weatherAPI_KEY)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    log.error(new String(res.getBody().readAllBytes()));
                    throw new UnknownAPIException("unable to get response from Weather API");
                })
                .body(ApiResponse.class)
                ;

        assert weatherApiResponse != null;
        //returning ApiResponseDto for better clarity of LLM, as after serialization, ApiResponse becomes a nested JSON
        return ApiResponseDto.of(weatherApiResponse);
    }
}
