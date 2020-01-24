package br.com.ifood.climateservice.infrastructure.port.application;

import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;

public interface ClimateApplicationPort {

    ClimateDTO getClimate(String lat, String lon, String city);

}
