package br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    private List<GenericResponseDTO> items;
}
