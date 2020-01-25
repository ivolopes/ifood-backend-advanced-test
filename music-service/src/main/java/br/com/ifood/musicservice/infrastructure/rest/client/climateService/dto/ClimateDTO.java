package br.com.ifood.musicservice.infrastructure.rest.client.climateService.dto;

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
    private boolean unavailable;
}
