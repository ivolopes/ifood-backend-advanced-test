package br.com.ifood.musicservice.infrastructure.adapter.data;

import br.com.ifood.musicservice.infrastructure.port.data.ClimateDataPort;
import br.com.ifood.musicservice.infrastructure.rest.client.climateService.ClimateServiceClient;
import br.com.ifood.musicservice.infrastructure.rest.client.climateService.dto.ClimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClimateData implements ClimateDataPort {

    private ClimateServiceClient climateServiceClient;

    @Autowired
    public ClimateData(ClimateServiceClient climateServiceClient){
        this.climateServiceClient = climateServiceClient;
    }

    @Override
    public ClimateDTO getWeather(String cityName, String lat, String lon) {
        return climateServiceClient.getWeather(cityName, lat, lon);
    }
}
