package br.com.ifood.musicservice.infrastructure.port.data;

import br.com.ifood.musicservice.infrastructure.rest.client.climateService.dto.ClimateDTO;

public interface ClimateDataPort {
    ClimateDTO getWeather(String token, String cityName, String lat, String lon);
}
