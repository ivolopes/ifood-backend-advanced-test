package br.com.ifood.climateservice.application;

import br.com.ifood.climateservice.framework.exceptions.BadRequestException;
import br.com.ifood.climateservice.infrastructure.port.application.ClimateApplicationPort;
import br.com.ifood.climateservice.infrastructure.port.data.ClimateDataPort;
import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClimateApplication implements ClimateApplicationPort {

    private ClimateDataPort climateData;

    @Autowired
    public ClimateApplication(ClimateDataPort climateData){
        this.climateData = climateData;
    }

    @Override
    public ClimateDTO getClimate(String lat, String lon, String city) {

        if( city != null && !city.isEmpty() ){
            return climateData.getClimateByCity(city);
        }else if( lat != null && lon != null &&
                !lat.isEmpty() && !lon.isEmpty() ){
            return climateData.getClimateByLatLon(lat, lon);
        }else{
            throw new BadRequestException("City name or latitude and longitude are required");
        }

    }

}
