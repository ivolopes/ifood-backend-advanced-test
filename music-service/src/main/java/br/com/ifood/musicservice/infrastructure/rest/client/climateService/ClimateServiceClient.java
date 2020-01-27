package br.com.ifood.musicservice.infrastructure.rest.client.climateService;

import br.com.ifood.musicservice.framework.configuration.FeignEncoderConfig;
import br.com.ifood.musicservice.infrastructure.rest.client.climateService.dto.ClimateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "climateServiceClient", url = "http://localhost:8080/climate-service/api/v1/climates",
        fallback = ClimateServiceClientFallback.class, configuration = FeignEncoderConfig.class)
public interface ClimateServiceClient {

    @GetMapping
    ClimateDTO getWeather(@RequestHeader("Authorization") String token,
                          @RequestParam(required = false) String city,
                          @RequestParam(required = false) String lat,
                          @RequestParam(required = false) String lon);
}

@Component
class ClimateServiceClientFallback implements ClimateServiceClient{

    @Override
    public ClimateDTO getWeather(String token, String cityName, String lat, String lon) {
        return ClimateDTO.builder()
                .city(cityName)
                .latitude(lat)
                .longitude(lon)
                .unavailable(true)
                .build();
    }
}