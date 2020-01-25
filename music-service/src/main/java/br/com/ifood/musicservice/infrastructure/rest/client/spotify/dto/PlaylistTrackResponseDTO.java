package br.com.ifood.musicservice.infrastructure.rest.client.spotify.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistTrackResponseDTO {
    private List<TrackResponseDTO> items;
}
