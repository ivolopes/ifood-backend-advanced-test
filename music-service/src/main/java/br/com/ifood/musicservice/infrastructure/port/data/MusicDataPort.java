package br.com.ifood.musicservice.infrastructure.port.data;

import br.com.ifood.musicservice.infrastructure.rest.controller.dto.GenericDTO;

import java.util.List;

public interface MusicDataPort {
    List<GenericDTO> findPlaylistsByCategory(String category);
    List<GenericDTO> findTracksByPlaylistId(String playlistId);
}
