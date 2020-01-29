package br.com.ifood.climateservice.infrastructure.rest.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO {

    private String name;
    private SystemDTO sys;
    private MainDTO main;
    private CoordinateDTO coord;
    private boolean unavailable = false;
    private String message;

    public WeatherDTO(){}
    public WeatherDTO(boolean unavailable){
        this.unavailable = unavailable;
    }

}
