package br.com.ifood.climateservice.infrastructure.rest.client.dto;

import lombok.Getter;

@Getter
public class WeatherDTO {

    private String name;
    private SystemDTO sys;
    private MainDTO main;
    private CoordinateDTO coord;
    private boolean unavailable = false;

    public WeatherDTO(){}
    public WeatherDTO(boolean unavailable){
        this.unavailable = unavailable;
    }

}
