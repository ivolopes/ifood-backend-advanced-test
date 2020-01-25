package br.com.ifood.musicservice.infrastructure.port.application;

import br.com.ifood.musicservice.infrastructure.rest.controller.dto.GenericDTO;

import java.util.List;

public interface TrackApplicationPort {
    List<GenericDTO> getTracks(String cityName, String lat, String lon);
}
