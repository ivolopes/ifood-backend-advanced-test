package br.com.ifood.climateservice.application;

import br.com.ifood.climateservice.infrastructure.port.application.ClimateApplicationPort;
import br.com.ifood.climateservice.infrastructure.port.data.ClimateDataPort;
import br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto.ClimateDTO;
import br.com.ifood.securitylib.exceptions.BadRequestException;
import br.com.ifood.securitylib.exceptions.NotFoundException;
import br.com.ifood.securitylib.exceptions.ServiceUnavailableException;
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

        ClimateDTO climateDTO;

        if( city != null && !city.isEmpty() ){
            climateDTO = climateData.getClimateByCity(city);
        }else if( lat != null && lon != null &&
                !lat.isEmpty() && !lon.isEmpty() ){
            climateDTO = climateData.getClimateByLatLon(lat, lon);
        }else{
            throw new BadRequestException("City name or latitude and longitude are required");
        }

        validateUnavailable(climateDTO);

        return climateDTO;
    }

    private void validateUnavailable(ClimateDTO response){
        if( response.isUnavailable() ){
            throw new ServiceUnavailableException("It is not possible to search the city' temperature");
        }else if( response.getMessage() != null && !response.getMessage().trim().equals("") ){
            throw new NotFoundException(response.getMessage());
        }
    }
}
