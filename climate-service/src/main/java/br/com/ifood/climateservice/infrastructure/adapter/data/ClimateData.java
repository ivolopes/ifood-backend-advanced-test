package br.com.ifood.climateservice.infrastructure.adapter.data;

import br.com.ifood.climateservice.framework.exceptions.ServiceUnavailableException;
import br.com.ifood.climateservice.infrastructure.port.data.ClimateDataPort;
import br.com.ifood.climateservice.infrastructure.rest.client.WeatherMapClient;
import br.com.ifood.climateservice.infrastructure.rest.client.dto.WeatherDTO;
import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ClimateData implements ClimateDataPort {

    @Value("${weatherMapClient.id}")
    private String id;

    @Value("${weatherMapClient.appId}")
    private String appId;

    @Value("${weatherMapClient.units}")
    private String units;

    private WeatherMapClient weatherMapClient;

    @Autowired
    public ClimateData(WeatherMapClient weatherMapClient){
        this.weatherMapClient = weatherMapClient;
    }

    @Override
    public ClimateDTO getClimateByCity(String city) {

        final WeatherDTO response = weatherMapClient.getWeather(id, appId, units, city, null, null);

        validateUnavailable(response);

        return convertToDTO(response);
    }

    @Override
    public ClimateDTO getClimateByLatLon(String lat, String lon) {
        final WeatherDTO response = weatherMapClient.getWeather(id, appId, units, null, lat, lon);

        validateUnavailable(response);

        return convertToDTO(response);
    }

    private void validateUnavailable(WeatherDTO response){
        if( response.isUnavailable() ){
            throw new ServiceUnavailableException("service unavailable");
        }
    }

    private ClimateDTO convertToDTO(WeatherDTO response){

        Double temp = Double.parseDouble(response.getMain().getTemp());
        return ClimateDTO.builder()
                .city(response.getName())
                .country(response.getSys().getCountry())
                .latitude(response.getCoord().getLat())
                .longitude(response.getCoord().getLon())
                .temperature(temp.intValue())
                .build();
    }
}
