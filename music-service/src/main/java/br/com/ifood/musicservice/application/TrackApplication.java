package br.com.ifood.musicservice.application;

import br.com.ifood.musicservice.infrastructure.port.application.TrackApplicationPort;
import br.com.ifood.musicservice.infrastructure.port.data.ClimateDataPort;
import br.com.ifood.musicservice.infrastructure.port.data.MusicDataPort;
import br.com.ifood.musicservice.infrastructure.rest.client.climateService.dto.ClimateDTO;
import br.com.ifood.musicservice.infrastructure.rest.controller.dto.GenericDTO;
import br.com.ifood.securitylib.exceptions.BadRequestException;
import br.com.ifood.securitylib.exceptions.NotFoundException;
import br.com.ifood.securitylib.exceptions.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackApplication implements TrackApplicationPort {

    private ClimateDataPort climateData;
    private MusicDataPort musicData;

    @Autowired
    public TrackApplication(ClimateDataPort climateData, MusicDataPort musicData){
        this.climateData = climateData;
        this.musicData = musicData;
    }

    @Override
    public List<GenericDTO> getTracks(String token, String cityName, String lat, String lon) {

        validateParams(cityName, lat, lon);

        final ClimateDTO weather = climateData.getWeather(token, cityName, lat, lon);

        validateClimateResponse(weather);

        String category = getCategoryByTemperature(weather.getTemperature());

        final List<GenericDTO> playlists = musicData.findPlaylistsByCategory(category);

        if( playlists != null && playlists.size() > 0 ){
            return this.musicData.findTracksByPlaylistId(playlists.get(0).getId());
        }

        return null;
    }

    private void validateClimateResponse(ClimateDTO response){
        if( response.isUnavailable() ){
            throw new ServiceUnavailableException("It is not possible to get the tracks");
        }else if( response.getMessage() != null && !response.getMessage().trim().equals("")){
            throw new NotFoundException(response.getMessage());
        }
    }

    private String getCategoryByTemperature(Integer temp){
        if( temp > 30){
            return "party";
        }else if(temp >= 15){
            return "pop";
        }else if(temp >= 10){
            return "rock";
        }else{
            return "classical";
        }
    }

    private void validateParams(String city, String lat, String lon){
        if( ( city == null || city.isEmpty() ) &&
                ( lat == null || lat.isEmpty() ) &&
                ( lon == null || lon.isEmpty() ) ){
            throw new BadRequestException("City name or latitude and longitude are required");
        }
    }

}
