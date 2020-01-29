package br.com.ifood.climateservice.infrastructure.rest.controller.v1.climate.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(onConstructor_ = @Builder)
public class ClimateDTO {

    private String latitude;
    private String longitude;
    private Integer temperature;
    private String city;
    private String country;

    @JsonIgnore
    private String message;
    @JsonIgnore
    private boolean unavailable;
}
