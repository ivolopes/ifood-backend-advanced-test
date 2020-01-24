package br.com.ifood.climateservice.infrastructure.port.data;

import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;

public interface ClimateDataPort {
    ClimateDTO getClimateByCity(String city);
    ClimateDTO getClimateByLatLon(String lat, String lon);
}
